package activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whastappclone.R;
import com.example.whastappclone.databinding.ActivityLoginBinding;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import preferencias.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

                Log.d("TOKEN", " "+token);
                Log.d("NOME", " "+nome);
                Log.d("TELEFONE", " "+enderecoCompleto);

                Preferencias preferencias = new Preferencias(view.getContext());
                preferencias.salvarPreferencesUsuario(nome, enderecoCompleto, token);
                HashMap<String, String> usuario = preferencias.getRecuperaPreferencesUsuario();

                Log.d("NOME PREF", " "+usuario.get("nome"));
                Log.d("TELEFONE PREF", " "+usuario.get("telefone"));
                Log.d("TOKEN PREF", " "+usuario.get("token"));
            }
        });

    }
}