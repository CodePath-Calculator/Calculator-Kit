package group1.android.calculatorkit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AnglesPentagonActivity extends AppCompatActivity {

    // Used for savedInstanceState
    private static final String KEY_INDEX = "index";

    EditText etAngle1;
    EditText etAngle2;
    EditText etAngle3;
    EditText etAngle4;
    Button btnFindMissingAngle;
    TextView tvMissingAngle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angles_pentagon);

        etAngle1 = findViewById(R.id.etAngle1);
        etAngle2 = findViewById(R.id.etAngle2);
        etAngle3 = findViewById(R.id.etAngle3);
        etAngle4 = findViewById(R.id.etAngle4);
        btnFindMissingAngle = findViewById(R.id.btnFindMissingAngle);
        tvMissingAngle = findViewById(R.id.tvMissingAngle);

        etAngle1.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        etAngle2.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        etAngle3.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        etAngle4.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        // Checks if savedInstanceState is null
        // This happens because it hasn't been initialized yet
        // until after first activity is created
        // If not null, retrieve the missing angle data
        // This makes sure the data gets retrieved after configuration changes.
        if (savedInstanceState != null) {
            tvMissingAngle.setText(savedInstanceState.getString(KEY_INDEX, "Please Input Angle(s)"));
        }

        btnFindMissingAngle.setOnClickListener(v -> {

            String angle1 = etAngle1.getText().toString();
            String angle2 = etAngle2.getText().toString();
            String angle3 = etAngle3.getText().toString();
            String angle4 = etAngle4.getText().toString();

            tvMissingAngle.setText("");

            // Check each angle to see if they are between 1 - 536
            boolean angle1IsValid = isAngleValid(angle1, "1");
            boolean angle2IsValid = isAngleValid(angle2, "2");
            boolean angle3IsValid = isAngleValid(angle3, "3");
            boolean angle4IsValid = isAngleValid(angle4, "4");

            // If all are valid, compute missing angles
            if (angle1IsValid && angle2IsValid && angle3IsValid && angle4IsValid) {

                // Changes the angle from String to int
                // Also, if angle is an empty string, treat it as 0
                int intAngle1 = getAngle(angle1);
                int intAngle2 = getAngle(angle2);
                int intAngle3 = getAngle(angle3);
                int intAngle4 = getAngle(angle4);

                // Sum all angles
                int sumOfAngles = intAngle1 + intAngle2 + intAngle3 + intAngle4;

                // Check if sum is valid
                // If the sum of angles are above 539,
                // do not compute since no pentagon
                // can be above 540 degrees and a missing
                // degree must be at least 1.
                if (!isSumValid(sumOfAngles)) {
                    return;
                }

                // Find the missing angle(s) by subtracting by the sum by 540.
                int angle5OrMissingAngles = 540 - sumOfAngles;

                // If there were any angles without inputs
                // Then there are more than one missing angle to find.
                if (angle1.matches("") || angle2.matches("")
                        || angle3.matches("") || angle4.matches("")) {

                    // If the missing angle range is 1, all angles are 1.
                    if (angle5OrMissingAngles == 1) {
                        String missingAngles = "All missing angles are 1 degree.";
                        tvMissingAngle.setText(missingAngles);
                        return;
                    }

                    // Set answer by setting the text as the following.
                    String missingAngles = "The missing angles can be between 1 and " + angle5OrMissingAngles;
                    tvMissingAngle.setText(missingAngles);
                    return;
                }

                // If there is only one missing angle, set the text as just a number.
                tvMissingAngle.setText(Integer.toString(angle5OrMissingAngles));
            }
        });
    }

    /**
     * Returns false if angle is 0 or greater than 536.
     * If not, the angle is valid or an empty string, return true.
     * @param angle       String of the angle
     * @param angleNumber String of the angle's number
     * @return boolean    Whether or not the angle is valid
     */
    private boolean isAngleValid(String angle, String angleNumber) {

        if (angle.matches("")) return true;

        int intAngle = Integer.parseInt(angle);

        if (intAngle > 536) {
            Toast.makeText(this, "Invalid input: Angle " + angleNumber + " should not be greater than 536 degrees.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (intAngle == 0) {
            Toast.makeText(this, "Invalid input: Angle " + angleNumber + " should not be 0 degrees.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * Returns 1 if string is empty.
     * If not, returns input string as integer
     * @param angle The input angle as string
     * @return int   The input angle or 1
     */
    private int getAngle(String angle) {

        if (angle.matches("")) return 1;

        return Integer.parseInt(angle);
    }

    /**
     * Returns a boolean if the sum if greater than 539.
     * @param sumOfAngles Integer of the sum of angles
     * @return boolean    Whether or not sum is valid.
     */
    private boolean isSumValid(int sumOfAngles) {
        if (sumOfAngles > 539) {
            Toast.makeText(this, "Invalid input: Sum of angles should not be greater than or equal to 539.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
    Saves missing angle answer across configuration changes.
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_INDEX, tvMissingAngle.getText().toString());
    }


    /**
     * Class is needed to change password input text that shows bullets as text
     * to not show bullets
     */
    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }
}
