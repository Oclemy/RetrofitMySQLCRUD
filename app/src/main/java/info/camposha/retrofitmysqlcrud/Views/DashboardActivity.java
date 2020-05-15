package info.camposha.retrofitmysqlcrud.Views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import info.camposha.retrofitmysqlcrud.Helpers.Utils;
import info.camposha.retrofitmysqlcrud.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class DashboardActivity extends AppCompatActivity {

    //We have 4 cards in the dashboard
    LinearLayout viewScientistsCard;
    LinearLayout addScientistCard;
    LinearLayout third;
    LinearLayout closeCard;
    /**
     * Let's initialize our cards  and listen to their click events
     */
    private void initializeWidgets(){
        viewScientistsCard = findViewById(R.id.viewScientistsCard);
        addScientistCard = findViewById(R.id.addScientistCard);
        third = findViewById(R.id.third);
        closeCard = findViewById(R.id.closeCard);

		viewScientistsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				Utils.openActivity(DashboardActivity.this,ScientistsActivity.class);

            }
        });
        addScientistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				Utils.openActivity(DashboardActivity.this,CRUDActivity.class);

            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.showInfoDialog(DashboardActivity.this, "YEEES",
                "Hey You can Display another page when this is clicked");

            }
        });
        closeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				finish();

            }
        });
    }
    /**
     * Let's override the attachBaseContext() method
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    /**
     * When the back button is pressed finish this activity
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /**
     * Let's override the onCreate() and call our initializeWidgets()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        this.initializeWidgets();
    }
}
//end


