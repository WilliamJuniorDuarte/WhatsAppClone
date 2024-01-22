package activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whastappclone.R;
import com.example.whastappclone.databinding.ActivityLoginBinding;
import com.example.whastappclone.databinding.ActivityValidacaoBinding;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import preferencias.Preferencias;

public class ValidacaoActivity extends AppCompatActivity {

    private ActivityValidacaoBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        binding = ActivityValidacaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText codigoSMS = binding.edtCodSMS;
        Button validacaoSMS = binding.btValidar;

        SimpleMaskFormatter smfCodSMS = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher mtwCodSMS = new MaskTextWatcher(codigoSMS, smfCodSMS);
        codigoSMS.addTextChangedListener(mtwCodSMS);

        Preferencias preferencias = new Preferencias(ValidacaoActivity.this);
        HashMap<String, String> dados = preferencias.getRecuperaPreferencesUsuario();

        validacaoSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codigoSMS.getText().toString().equals(dados.get("token")) ){
                    Toast.makeText(ValidacaoActivity.this, "Token válido!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ValidacaoActivity.this, "Token inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}