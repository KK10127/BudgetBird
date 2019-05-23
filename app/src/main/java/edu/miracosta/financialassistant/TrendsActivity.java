package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Account;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class TrendsActivity extends AppCompatActivity {

    private LineChartView lineChartView;
    private Intent mIntent;
    private Account mAccount;

    /**
     * <p>This starts the activity</p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        //Don't change this.
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");

        // wiring up the line chart
        lineChartView = findViewById(R.id.trendsLineChart);


        ////////////////////////////////////////////////////////////////////////////
        // DATA PROCESSING CENTER //////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////

        String[] xAxisData;
        Float[] yAxisData;

        // start the db helper
        DBHelper db = new DBHelper(this);
        long numRecords = db.getDaysCount();
        if (numRecords > 1) {
            xAxisData = new String[(int) numRecords];

            for (int i = 0; i < xAxisData.length ;i++ ) {
                xAxisData[i] = "";
            }

            xAxisData[1] = "" + (numRecords - 1) + " days ago";
            xAxisData[(int) (numRecords - 1)] = "Current day";

            yAxisData = new Float[(int) numRecords];
            List<Float> recordValues = db.getSpendingTrends();

            for (int i = 0; i < yAxisData.length ;i++ ) {
                yAxisData[i] = recordValues.get(i);
            }

        } else {
            xAxisData = new String[1];
            yAxisData = new Float[1];
            xAxisData[0] = "No data to display!";
            yAxisData[0] = null;
        }

        // xAxisData is the X axis of the chart. These values must be changed
        // when switching from past weekt to month/year.
        //xAxisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
          //      "Oct", "Nov", "Dec"};

        // data on the Y axis that corresponds to each indice on the X-axis.


        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////


        // lists for the values
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();

        //
        Line line = new Line(yAxisValues);

        //
        for(int i = 0; i < xAxisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            if (yAxisData[i] != null)
                yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        //
        List lines = new ArrayList();
        lines.add(line);

        //
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //
        lineChartView.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);

        Viewport v = lineChartView.getMaximumViewport();
        float dy = v.height() * 0.2f;
        v.inset(0, -dy);
        lineChartView.setMaximumViewport(v);
        lineChartView.setCurrentViewport(v);
    }
}
