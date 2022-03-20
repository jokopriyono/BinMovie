package com.bin.movie.ui.upload

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bin.movie.databinding.FragmentUploadBinding
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.roundToInt
import kotlin.math.sqrt

class UploadFragment : Fragment() {

    private val viewModel: UploadViewModel by activityViewModels()

    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private val requestGetPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedUri: Uri? = result.data?.data
            selectedUri?.let { uri ->
                binding.imgPreview.setImageURI(uri)
                createFileBeforeUpload(uri)
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Pemilihan gambar dibatalkan",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSelectAndUpload.setOnClickListener {
            requestAccessForFile()
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.visibility =
                if (it) View.VISIBLE else View.GONE
        }
        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.uploadResponse.observe(viewLifecycleOwner) {
            var txtString = ""
            txtString += "Code: ${it.data.code}\n"
            txtString += "Link: ${it.data.directLink}\n"
            txtString += "File ID: ${it.data.fileId}\n"
            txtString += "File Name: ${it.data.fileName}\n"
            txtString += "Info: ${it.data.info}\n"
            txtString += "MD5: ${it.data.md5}\n"
            binding.txtResult.text = txtString
        }
    }

    private fun requestAccessForFile() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                777
            )
        } else {
            selectFileForUpload()
        }

    }

    private fun selectFileForUpload() {
        val i = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        requestGetPhoto.launch(Intent.createChooser(i, "Select Picture"))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 777) {
            if (
                grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                selectFileForUpload()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Aplikasi memerlukan izin anda",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createFileBeforeUpload(uri: Uri) {
        lifecycleScope.launch {
            kotlin.runCatching {
                try {
                    val targetOriginal =
                        BitmapFactory.decodeStream(
                            requireActivity().contentResolver.openInputStream(
                                uri
                            )
                        )
                    val targetCompressed = reduceBitmapSize(targetOriginal)
                    val bos = ByteArrayOutputStream()
                    targetCompressed.compress(Bitmap.CompressFormat.JPEG, 0, bos)
                    val bitmapData = bos.toByteArray()
                    val file = File(
                        requireActivity().filesDir.path,
                        "${System.currentTimeMillis()}_compressed.jpeg"
                    )
                    file.createNewFile()
                    val fos = FileOutputStream(file)
                    fos.write(bitmapData)
                    fos.flush()
                    fos.close()

                    // lempar file ke view model untuk diupload retrofit
                    viewModel.uploadImageToServer(file)
                } catch (e: Exception) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Terjadi kesalahan saat menulis file",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun reduceBitmapSize(bitmap: Bitmap, MAX_SIZE: Int = 360000): Bitmap {
        val ratioSquare: Double
        val bitmapHeight: Int = bitmap.height
        val bitmapWidth: Int = bitmap.width
        ratioSquare = (bitmapHeight * bitmapWidth / MAX_SIZE).toDouble()
        if (ratioSquare <= 1) return bitmap
        val ratio = sqrt(ratioSquare)
        val requiredHeight = (bitmapHeight / ratio).roundToInt()
        val requiredWidth = (bitmapWidth / ratio).roundToInt()
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true)
    }
}