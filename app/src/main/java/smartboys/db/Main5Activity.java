package smartboys.db;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        drawPieChart();

    }

    private void drawPieChart() {
        // Create CategorySeries
       final CategorySeries categorySeries = new CategorySeries("Pie chart categories");

        IncomeDB db = new IncomeDB(this);
        Cursor c2 = db.displayInc();
        if (c2.getCount() == 0)
            Toast.makeText(getApplicationContext(), "Error..!! nothing to display..!", Toast.LENGTH_SHORT).show();
        else {
            c2.moveToFirst();
            categorySeries.add(c2.getString(0),c2.getInt(1));
            while (c2.moveToNext()) {
                categorySeries.add(c2.getString(0), c2.getInt(1));
            }

            // Add categories
       /* categorySeries.add("Gift",31200);
        categorySeries.add("Salary",21200);
        categorySeries.add("Other",30000);*/


            // Add series render to each slide of the pie chart
            int[] colors = {Color.GREEN, Color.RED, Color.GRAY};
          final   DefaultRenderer defaultRenderer = new DefaultRenderer();
            for (int i = 0; i < categorySeries.getItemCount(); i++) {
                XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();
                // Specify render options
                seriesRenderer.setColor(colors[i]);
                seriesRenderer.setDisplayChartValues(true);
                seriesRenderer.setDisplayChartValuesDistance(0);
                seriesRenderer.setChartValuesTextSize(100);
                seriesRenderer.setShowLegendItem(true);
                defaultRenderer.addSeriesRenderer(seriesRenderer);
                defaultRenderer.setLabelsTextSize(30);
                defaultRenderer.setLabelsColor(Color.BLACK);
            }

            // Specify default render options
            defaultRenderer.setPanEnabled(true);
            defaultRenderer.setAntialiasing(true);
            defaultRenderer.setShowLabels(true);
            defaultRenderer.setShowLegend(true);
            defaultRenderer.setChartTitle("Pie Chart");
            defaultRenderer.setChartTitleTextSize(30);

            // Get pie chart view
           final GraphicalView chartView = ChartFactory.getPieChartView(this, categorySeries, defaultRenderer);
            // Add the pie chart view to the layout to show
            LinearLayout chartlayout = (LinearLayout) findViewById(R.id.llayout);
            chartlayout.addView(chartView);
            defaultRenderer.setClickEnabled(true);
            chartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeriesSelection seriesSelection = chartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(getApplicationContext(), "No chart element selected", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        for (int i = 0; i < categorySeries.getItemCount(); i++) {
                            defaultRenderer.getSeriesRendererAt(i).setHighlighted(i == seriesSelection.getPointIndex());
                        }
                        chartView.repaint();
                        Toast.makeText(
                                getApplicationContext(),
                                "Chart data point index " + seriesSelection.getPointIndex() + " selected"
                                        + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

