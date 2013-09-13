package com.example.pizzaz;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity
{
	TextView price;
	RadioButton s;
	RadioButton m;
	RadioButton l;
	CheckBox anchovies;
	CheckBox cheesy;
	CheckBox burnt;
	
	private double cost = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		price = (TextView) findViewById(R.id.textView1);
		s = (RadioButton) findViewById(R.id.radio0);
		m = (RadioButton) findViewById(R.id.radio1);
		l = (RadioButton) findViewById(R.id.radio2);
		anchovies = (CheckBox) findViewById(R.id.checkBox1);
		cheesy = (CheckBox) findViewById(R.id.checkBox2);
		burnt = (CheckBox) findViewById(R.id.checkBox3);
		calcPrice();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void calcPrice(){
		cost = 0;
		if(s.isChecked())
			cost = 10;
		else if(m.isChecked())
			cost = 15;
		else if(l.isChecked())
			cost = 19.99;
		
		if(anchovies.isChecked())
			cost += 2.50;
		if(cheesy.isChecked())
			cost += 5.99;
		if(burnt.isChecked())
			cost += 60;
		int i = (int) (cost*100);
		cost = ((double)i)/100;
		price.setText("$" + cost);
	}
	
	public void checkOut(View v){
		calcPrice();
		Intent i = new Intent(getApplicationContext(), CheckOutActivity.class);
		i.putExtra("price", cost);
		if(s.isChecked())
			i.putExtra("size", "small");
		else if(m.isChecked())
			i.putExtra("size", "medium");
		else if(l.isChecked())
			i.putExtra("size", "large");
		List<String> plus = new ArrayList<String>();
		if(anchovies.isChecked())
			plus.add("Anchovies");
		if(cheesy.isChecked())
			plus.add("Cheesy Crust");
		if(burnt.isChecked())
			plus.add("Burnt to a Crust");
		i.putExtra("plus", plus.toArray());
		startActivity(i);
	}
	
	public void click(View v){
		calcPrice();
	}

}
