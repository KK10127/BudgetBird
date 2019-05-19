package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.model.Account;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class TrendsActivity extends AppCompatActivity {

    private LineChartView lineChartView;
    private Intent mIntent;
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        //Don't change this.
        mIntent = getIntent();
        mAccount = mIntent.getParcelableExtra("Account");

        // wiring up the line chart
        lineChartView = findViewById(R.id.trendsLineChart);

        // xAxisData is the X axis of the chart. These values must be changed
        // when switching from past weekt to month/year.
        String[] xAxisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"};

        // data on the Y axis that corresponds to each indice on the X-axis.
        int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

        // lists for the values
        List yAxisValues = new ArrayList();
        List xAxisValues = new ArrayList();

        //
        Line line = new Line(yAxisValues);

        //
        for(int i = 0; i < xAxisData.length; i++){
            xAxisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
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
    }
}
