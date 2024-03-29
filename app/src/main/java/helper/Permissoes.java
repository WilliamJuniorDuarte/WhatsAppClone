package helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissoes {
    public static boolean validaPermissoes(int requestCode, Activity activity, String[] permissoes){

        if (Build.VERSION.SDK_INT >= 23){
            List<String> listaPermissoes = new ArrayList<String>();

            for(String permissao : listaPermissoes){
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if (!validaPermissao)
                    listaPermissoes.add(permissao);
            }

            if (listaPermissoes.isEmpty())
                return true;

            String[] novaPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novaPermissoes);

            ActivityCompat.requestPermissions(activity, novaPermissoes, requestCode);
        }
        return true;
    }

}
