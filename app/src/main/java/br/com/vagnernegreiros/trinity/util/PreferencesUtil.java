package br.com.vagnernegreiros.trinity.util;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.vagnernegreiros.trinity.model.Usuario;

public class PreferencesUtil {

    public static void saveUsuario(Usuario usuario , Context context){
        SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("MY_PREFERENCES" , Context.MODE_PRIVATE).edit();

        editor.putString("email", usuario.getUser_email());

        editor.apply();
    }

    public static Usuario getUsuario(Context context){
        SharedPreferences prefs = context.getApplicationContext().getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        String email = prefs.getString("email", "");
        return new Usuario(email);
    }

    public static void clearPrefs(Context context){
        SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("MY_PREFERENCES" , Context.MODE_PRIVATE).edit();

        editor.remove("email");
        editor.clear();
        editor.apply();
    }

}
