package ca.yorku.eecs.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.crypto.Cipher;

public class KryptoNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }
    /**
    *This function will encrypt the text
    **/
    public void onEncrypt(View v){
        try
        {
            EditText keyView = (EditText) findViewById(R. id.key);
            String key = keyView.getText().toString();
            if (key.length() != 0){
                EditText dataView = (EditText) findViewById(R. id.data);
                String text = dataView.getText().toString();

                CipherModel myModel = new CipherModel(key);
                String encode = myModel.encrypt(text);

                ((EditText) findViewById(R.id.data)).setText(encode);
            }else{
                Toast.makeText(this, "key should not be empty", Toast.LENGTH_LONG).show();
            }


        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    /**
    *This function will decrypt the text
    **/
    public void onDecrypt(View v){
        EditText keyView = (EditText) findViewById(R. id.key);
        String key = keyView.getText().toString();
        if (key.length() != 0){
            EditText dataView = (EditText) findViewById(R. id.data);
            String text = dataView.getText().toString();

            CipherModel myModel = new CipherModel(key);
            String decode = myModel.decrypt(text);

            ((EditText) findViewById(R.id.data)).setText(decode);
        }else{
            Toast.makeText(this, "key should not be empty", Toast.LENGTH_LONG).show();
        }

    }
    /**
    *This function will save the text
    **/
    public void onSave(View v){
        try{
            String name =((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    /**
    *This function will load the text from memory
    **/
    public void onLoad(View v){
        try{
            String name =((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read()){
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
