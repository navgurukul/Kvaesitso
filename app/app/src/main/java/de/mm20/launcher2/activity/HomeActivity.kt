package de.mm20.launcher2.activity

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.mm20.launcher2.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnSetDefault = findViewById<Button>(R.id.btnSetDefault)

        btnSetDefault.setOnClickListener {
            if (!isDefaultLauncher()) {
                promptSetAsDefaultLauncher()
            } else {
                Toast.makeText(this, "Tez Launcher is already the default launcher", Toast.LENGTH_SHORT).show()
            }
        }

        if (!isDefaultLauncher()) {
            promptSetAsDefaultLauncher()
        }
    }

    private fun isDefaultLauncher(): Boolean {
        val pm = packageManager
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
        }
        val defaultLauncher = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)?.activityInfo?.packageName
        val currentLauncher = ComponentName(this, HomeActivity::class.java).packageName
        return currentLauncher == defaultLauncher
    }

    private fun promptSetAsDefaultLauncher() {
        AlertDialog.Builder(this)
            .setTitle("Set as Default Launcher")
            .setMessage("Would you like to set Tez Launcher as your default home screen?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
            }
            .setNegativeButton("No", null)
            .show()
    }
}
