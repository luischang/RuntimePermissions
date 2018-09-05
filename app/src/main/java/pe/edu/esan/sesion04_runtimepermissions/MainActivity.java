package pe.edu.esan.sesion04_runtimepermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Create by: Luis Chang
 *
 */


public class MainActivity extends AppCompatActivity {

    private Button botonPermiso;
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonPermiso = (Button) findViewById(R.id.btnSolicitarPermiso);

        botonPermiso.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (isReadStorageAllowed()) {
                    Toast.makeText(MainActivity.this, "Usted ya cuenta con el permiso", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestStoragePermission();
            }
        });
    }

    private boolean isReadStorageAllowed() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean exito = false;
        if (result == PackageManager.PERMISSION_GRANTED)
            exito = true;
        return exito;
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permiso ACEPTADO, ahora usted puede leer el almacenamiento", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Permiso DENEGADO, usted no tiene acceso al almacenamiento", Toast.LENGTH_SHORT).show();

            }
        }


    }


}
