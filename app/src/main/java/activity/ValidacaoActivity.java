package activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whastappclone.R;
import com.example.whastappclone.databinding.ActivityLoginBinding;
import com.example.whastappclone.databinding.ActivityValidacaoBinding;

public class ValidacaoActivity extends AppCompatActivity {

    private ActivityValidacaoBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        binding = ActivityValidacaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}