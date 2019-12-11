
package com.example.licet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main9Activity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    Spinner s1,s2;
    Button b1;
    File f;
    TextView t1,t2;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    String st1,st2,path;
    String department,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        databaseReference= FirebaseDatabase.getInstance().getReference("licet");
        databaseReference2= FirebaseDatabase.getInstance().getReference("licetyear");
        s1=findViewById(R.id.spinner);
        s2=findViewById(R.id.spinner2);
        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 7);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                   path = data.getData().getPath();
                    path=path.substring(path.indexOf(":")+1);
                   }
                if(path!=null)
                {
                    t1.setText("File Choosed");
                }
                else {
                    Toast.makeText(Main9Activity.this, "Please select a valid file", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    long numericValue = (long) cellValue.getNumberValue();
                        value = ""+numericValue;
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {
            Toast.makeText(Main9Activity.this, "getCellAsString: NullPointerException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return value;
    }





    public void readfile(View view) {
        if (path != null) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            f = new File(path);
            try {
                InputStream inputStream = new FileInputStream(f);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);
                int rowsCount = sheet.getPhysicalNumberOfRows();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                String name = null, regno = null, year = null, dept = null, phno = null, fname = null, mname = null, fnum = null, mnum = null, address = null;
                for (int r = 1; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    int cellsCount = row.getPhysicalNumberOfCells();

                    for (int c = 0; c < cellsCount; c++) {
                        String value = getCellAsString(row, c, formulaEvaluator);
                        if (c == 0) {
                            name = value;
                        } else if (c == 1) {
                            regno = value;
                        } else if (c == 2) {
                            year = value;
                        } else if (c == 3) {
                            dept = value;
                        } else if (c == 4) {
                            phno = value;
                        } else if (c == 5) {
                            fname = value;
                        } else if (c == 6) {
                            mname = value;
                        } else if (c == 7) {
                            fnum = value;
                        } else if (c == 8) {
                            mnum = value;
                        } else if (c == 9) {
                            address = value;
                        }
                    }

                    databaseReference.child("student").child(regno).child("name").setValue(name);
                    databaseReference.child("student").child(regno).child("reg").setValue(regno);
                    databaseReference.child("student").child(regno).child("year").setValue(year);
                    databaseReference.child("student").child(regno).child("dept").setValue(dept);
                    databaseReference.child("student").child(regno).child("phone").setValue(phno);
                    databaseReference.child("student").child(regno).child("fathername").setValue(fname);
                    databaseReference.child("student").child(regno).child("mothername").setValue(mname);
                    databaseReference.child("student").child(regno).child("fathernum").setValue(fnum);
                    databaseReference.child("student").child(regno).child("mothernum").setValue(mnum);
                    databaseReference.child("student").child(regno).child("address").setValue(address);


                    databaseReference2.child("student").child(dept).child(year).child(regno).child("name").setValue(name);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("reg").setValue(regno);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("year").setValue(year);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("dept").setValue(dept);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("phone").setValue(phno);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("fathername").setValue(fname);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("mothername").setValue(mname);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("fathernum").setValue(fnum);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("mothernum").setValue(mnum);
                    databaseReference2.child("student").child(dept).child(year).child(regno).child("address").setValue(address);

                }
                Toast.makeText(Main9Activity.this, "Students are sucessfully entered", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Main9Activity.this, Main2Activity.class);
                startActivity(i);
            } catch (FileNotFoundException e) {
                Toast.makeText(Main9Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(Main9Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Main9Activity.this, "Select the file to be opened", Toast.LENGTH_SHORT).show();
        }
            }

    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(this,Main7Activity.class);
        startActivity(intent1);
    }


}