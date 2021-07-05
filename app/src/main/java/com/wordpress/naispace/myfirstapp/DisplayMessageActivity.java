package com.wordpress.naispace.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

public class DisplayMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Attempt at opening Asset files
        String fracTxt = "";
        String numTxt = "";
        String metTxt = "";


        try {
            fracTxt = LoadFile("FractionalDrills.txt");
            numTxt = LoadFile("NumberDrills.txt");
            metTxt = LoadFile("MetricDrills.txt");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Log.i("Fractional Drills", fracTxt);
        //Log.i("Number Drills", numTxt);
        //Log.i("Metric Drills", metTxt);

        //The desired size
        Double myDiam = Double.valueOf(message);

        //Storage for two closest (over / under) drill sizes
        ClosePair closeFrac[] = findClosestSizes(fracTxt, myDiam);
        ClosePair closeNum[] = findClosestSizes(numTxt, myDiam);
        ClosePair closeMet[] = findClosestSizes(metTxt, myDiam);

        Log.i("Looking for", message);

        Log.i("Over Fractional", closeFrac[0].toString());
        Log.i("Under Fractional", closeFrac[1].toString());

        Log.i("Over Number", closeNum[0].toString());
        Log.i("Under Number", closeNum[1].toString());

        Log.i("Over Metric", closeMet[0].toString());
        Log.i("Under Metric", closeMet[1].toString());


        //Nonsense for testing
        Double myNum = Double.valueOf(message);
        myNum += 42.0;
        myNum *= 5;

        try
        {
            message = myNum.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(String.format(Locale.US, "%.5f", myDiam));

        //Set over/under texts
        //Fractional
        TextView overFrac = findViewById(R.id.textView7);
        overFrac.setText(closeFrac[0].toString(myDiam));

        TextView underFrac = findViewById(R.id.textView9);
        underFrac.setText(closeFrac[1].toString(myDiam));

        //Number/Letter
        TextView overNum = findViewById(R.id.textView14);
        overNum.setText(closeNum[0].toString(myDiam));

        TextView underNum = findViewById(R.id.textView15);
        underNum.setText(closeNum[1].toString(myDiam));

        //Metric
        TextView overMet = findViewById(R.id.textView19);
        overMet.setText(closeMet[0].toString(myDiam));

        TextView underMet = findViewById(R.id.textView20);
        underMet.setText(closeMet[1].toString(myDiam));


    }

    private String LoadFile (String fileName) throws IOException
    {
        //Create a InputStream to read the file into
        InputStream iS;

        Log.i("Opening: ", fileName);

        //get the file as a stream
        iS = getAssets().open(fileName);

        //create a buffer that has the same size as the InputStream
        byte[] buffer = new byte[iS.available()];
        //read the text file as a stream, into the buffer
        iS.read(buffer);
        //create a output stream to write the buffer into
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        //write this buffer to the output stream
        oS.write(buffer);
        //Close the Input and Output streams
        oS.close();
        iS.close();

        //return the output stream as a String
        return oS.toString();
    }

    private ClosePair[] findClosestSizes(String drillList, Double myDiam){
        ClosePair overUnder[] = new ClosePair[2];
        overUnder[0] = new ClosePair();
        overUnder[1] = new ClosePair();

        //Go through list line by line, up until we've found a drill bigger than myDiam
        for (String line: drillList.split("\\r?\\n")){
            String tokens[] = line.split("\\s");

            //Deal with the space before mm
            if (tokens.length > 2)
                tokens[1] = tokens[1] + tokens[2];

            overUnder[0].setClosePair(Double.valueOf(tokens[0]) , tokens[1] );

            if (overUnder[0].getNum() >= myDiam){
                //We're done
                break;
            }
            else {
                overUnder[1].setClosePair(overUnder[0].getNum(), overUnder[0].getTxt());
            }
        }

        return overUnder;
    }

}


