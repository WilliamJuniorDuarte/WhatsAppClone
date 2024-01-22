package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.whastappclone.R;
import com.example.whastappclone.databinding.ActivityCadastroUsuarioBinding;
import com.example.whastappclone.databinding.ActivityLoginBinding;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private ActivityCadastroUsuarioBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        binding = ActivityCadastroUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}