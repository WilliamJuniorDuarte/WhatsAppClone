package activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whastappclone.R;
import com.example.whastappclone.databinding.ActivityLoginBinding;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import helper.Permissoes;
import preferencias.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding = null;
    private String[] permissoesRequeridas = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Permissoes.validaPermissoes(1, this, permissoesRequeridas);

        SimpleMaskFormatter smkCodPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter smkCodArea = new SimpleMaskFormatter("NNN");
        SimpleMaskFormatter smkTelefone = new SimpleMaskFormatter("N NNNN-NNNN");

        MaskTextWatcher mtwCodPais = new MaskTextWatcher(binding.edtCodPais, smkCodPais);
        MaskTextWatcher mtwCodArea = new MaskTextWatcher(binding.edtCodArea, smkCodArea);
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(binding.edtTelefone, smkTelefone);

        binding.edtCodPais.addTextChangedListener(mtwCodPais);
        binding.edtCodArea.addTextChangedListener(mtwCodArea);
        binding.edtTelefone.addTextChangedListener(mtwTelefone);

        binding.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = binding.edtUsername.getText().toString();
                String enderecoCompleto = (binding.edtCodPais.getText().toString()
                                        + binding.edtCodArea.getText().toString()
                                        + binding.edtTelefone.getText().toString()).replace("+", "").replace("-", "");

                Random random = new Random();
                String token = String.valueOf(random.nextInt(9999-1000)+1000);
                String messageSMS = "WhatsApp Código de Confirmação: "+token;

                Log.d("TOKEN", " "+token);
                Log.d("NOME", " "+nome);
                Log.d("TELEFONE", " "+enderecoCompleto);

                Preferencias preferencias = new Preferencias(view.getContext());
                preferencias.salvarPreferencesUsuario(nome, enderecoCompleto, token);
//                HashMap<String, String> usuario = preferencias.getRecuperaPreferencesUsuario();


                boolean sms = enviaSMS("+"+enderecoCompleto, messageSMS );

                if (sms){
                    Intent intent = new Intent(LoginActivity.this, ValidacaoActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(view.getContext(), "Erro ao tentar enviar SMS, tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean enviaSMS(String telefone, String message){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, message, null, null);
            return true;
        }catch (Exception e){
            Log.d("ERROR_ENVIO_SMS", e.getMessage());
            return false;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);

        for (int resultado : grantResult){
            if (resultado == PackageManager.PERMISSION_DENIED){
                alertValidacaoPermissao();
            }
        }
    }

    private void alertValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar esse app é preciso aceitar as permissões!");
        builder.setPositiveButton("CONFIRMAR", (dialog, which)->{
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 1);
            finish();
        });

        AlertDialog

                alert = builder.create();
        alert.show();
    }
}