package com.lazarusid.tenoncalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class TenonActivity extends AppCompatActivity {

    private final static NumberFormat nf = NumberFormat.getInstance();

    private EditText edtLeg;
    private TextView txtLeg;
    private EditText edtRailWidth;
    private EditText edtTenonWidth;
    private TextView txtTenonWidth;
    private EditText edtFrontTenonShoulder;
    private TextView txtFrontTenonShoulder;
    private EditText edtRailSetback;
    private TextView txtRailSetback;
    private TextView txtMortiseShoulder;
    private TextView txtMortiseDepth;
    private TextView txtMortiseInsideDepth;
    private TextView txtRailWidth;

    private View.OnFocusChangeListener mFocusChanged = new View.OnFocusChangeListener(){
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                calculateTenon();
            }
        }
    };

    private TextView.OnEditorActionListener mActionListener = new EditText.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                calculateTenon();
                return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenon);

        nf.setMinimumIntegerDigits(1);
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(2);

        edtLeg = (EditText) findViewById(R.id.edtLeg);
        txtLeg = (TextView) findViewById(R.id.txtLeg);
        edtRailWidth = (EditText) findViewById(R.id.edtRailWidth);
        edtTenonWidth = (EditText) findViewById(R.id.edtTenonWidth);
        txtTenonWidth = (TextView) findViewById(R.id.txtTenonWidth);
        edtFrontTenonShoulder = (EditText) findViewById(R.id.edtFrontTenonShoulder);
        txtFrontTenonShoulder = (TextView) findViewById(R.id.txtFrontTenonShoulder);
        edtRailSetback = (EditText) findViewById(R.id.edtRailSetback);
        txtRailSetback = (TextView) findViewById(R.id.txtRailSetback);
        txtMortiseShoulder = (TextView) findViewById(R.id.txtMortiseShoulder);
        txtMortiseDepth = (TextView) findViewById(R.id.txtMortiseDepth);
        txtMortiseInsideDepth = (TextView) findViewById(R.id.txtMortiseInsideDepth);
        txtRailWidth = (TextView) findViewById(R.id.txtRailWidth);

        wireUp(edtLeg);
        wireUp(edtRailWidth);
        wireUp(edtTenonWidth);
        wireUp(edtFrontTenonShoulder);
        wireUp(edtRailSetback);

        edtLeg.requestFocus();

    }

    private void wireUp(EditText edt) {
        edt.setOnFocusChangeListener(mFocusChanged);
        edt.setOnEditorActionListener(mActionListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tenon, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("leg", edtLeg.getText().toString());
        outState.putString("rail_width", edtRailWidth.getText().toString());
        outState.putString("tenon_width", edtTenonWidth.getText().toString());
        outState.putString("front_tenon_shoulder", edtFrontTenonShoulder.getText().toString());
        outState.putString("rail_setback", edtRailSetback.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        String leg = savedState.getString("leg");
        String rail_width = savedState.getString("rail_width");
        String tenon_width = savedState.getString("tenon_width");
        String front_tenon_shoulder = savedState.getString("front_tenon_shoulder");
        String rail_setback = savedState.getString("rail_setback");


        edtLeg.setText(leg);
        edtRailWidth.setText(rail_width);
        edtTenonWidth.setText(tenon_width);
        edtFrontTenonShoulder.setText(front_tenon_shoulder);
        edtRailSetback.setText(rail_setback);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Double getEditValue(EditText edt) {
        Double value = 0.0;
        if (edt.length() > 0) {
            value = Double.parseDouble(edt.getText().toString());
        }
        return value;
    }

    public void calculateTenon() {

        TenonModel model = new TenonModel();

        model.setLeg(getEditValue(edtLeg));
        model.setRailWidth(getEditValue(edtRailWidth));
        model.setRailSetback(getEditValue(edtRailSetback));
        model.setFrontTenonShoulder(getEditValue(edtFrontTenonShoulder));
        model.setTenonWidth(getEditValue(edtTenonWidth));

        txtLeg.setText(nf.format(model.getLeg()));
        txtRailWidth.setText(nf.format(model.getRailWidth()));
        txtTenonWidth.setText(nf.format(model.getTenonWidth()));
        txtFrontTenonShoulder.setText(nf.format(model.getFrontTenonShoulder()));
        txtRailSetback.setText(nf.format(model.getRailSetback()));
        txtMortiseShoulder.setText(nf.format(model.getMortiseShoulder()));
        txtMortiseDepth.setText(nf.format(model.getMortiseDepth()));
        txtMortiseInsideDepth.setText(nf.format(model.getMortiseInsideDepth()));

    }
}
