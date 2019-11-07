package labrador.cse.usf.parsetest2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button click;
    public  static TextView data;
    public static String zip="";
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.fetcheddata);
        editText=findViewById(R.id.editText);
        //Intent i=new Intent(this,fetchData.class)
        //i.putExtra("zip",zip);
        //startActivity(i);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zip=editText.getText().toString();
                //data.setText(zip);
                fetchData process = new fetchData();
                process.execute();
            }
        });


    }
}
