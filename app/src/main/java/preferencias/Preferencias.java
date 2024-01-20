package preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class Preferencias {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String ARQUIVO_APP = "ZAP_ANDROID";
    private String CHAVE_NOME = "nome";
    private String CHAVE_TELEFONE = "telefone";
    private String CHAVE_TOKEN = "token";

    public Preferencias(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(ARQUIVO_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void salvarPreferencesUsuario(String nome, String telefone, String token){
        editor.putString(CHAVE_NOME, nome);
        editor.putString(CHAVE_TELEFONE, telefone);
        editor.putString(CHAVE_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getRecuperaPreferencesUsuario(){
        HashMap<String, String> dados = new HashMap<>();
        dados.put(CHAVE_NOME, sharedPreferences.getString(CHAVE_NOME, null));
        dados.put(CHAVE_TELEFONE, sharedPreferences.getString(CHAVE_TELEFONE, null));
        dados.put(CHAVE_TOKEN, sharedPreferences.getString(CHAVE_TOKEN, null));
        return dados;
    }
}
