package com.example.shenghuotong.huatu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;

import com.example.shenghuotong.R;

public class MainActivity_huatu extends Activity {

	//���ֻ�ͼ�����ֱࣺ�ߡ����Ρ�Բ�ȵ�
	private Draw_Line dLine;
	private Draw_Rectangle dRectangle;
	private Draw_Circle dCircle;
	private Draw_triangle dTriangle;
	private Draw_Cube dCube;
	private Draw_Column dColumn;
	private Draw_Path dPath;

	Button draw_btn;//ѡ��ͼ�εİ�ť
	
	/* ����ÿ��view�Ĳ��ִ�С
	 * This set of layout parameters defaults the width and the height 
	 * 
	 * LayoutParams.MATCH_PARENT��xml�ж���ؼ���С��match_parent����һ��
	 * height������Ϊ700���������Ϊmatch_parent��wrap_content��view��ռ��ȫ����סdraw_btn��ť�ؼ�
	 */
	LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT, 700);
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_huatu);
        
        draw_btn = (Button)findViewById(R.id.draw_button);
        draw_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/*
				 * ������ť��ᵯ��ѡ��ͼ�ζԻ���
				 */
				final String[] mItems = {"直线","矩形","圆形","三角形","立方体","圆柱体","涂鸦"};
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_huatu.this);
		        builder.setTitle("选择形状");
				builder.setItems(mItems, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// ѡ��󣬸���ѡ�������ʵ������Ӧ����
						switch (which) {
						case 0: //ֱ��
							//ʵ����ֱ��view��
							dLine = new Draw_Line(getApplicationContext());
							//��ֱ��view����뵽��ǰactivity
							addContentView(dLine, lParams);
							break;
						case 1:
							dRectangle = new Draw_Rectangle(getApplicationContext());
							addContentView(dRectangle, lParams);
							break;
						case 2:
							dCircle = new Draw_Circle(getApplicationContext());
							addContentView(dCircle, lParams);
							break;
						case 3:
							dTriangle = new Draw_triangle(getApplicationContext());
							addContentView(dTriangle,
									lParams);
							break;
						case 4:
							dCube = new Draw_Cube(getApplicationContext());
							addContentView(dCube, lParams);
							break;
						case 5:
							dColumn = new Draw_Column(getApplicationContext());
							addContentView(dColumn, lParams);
							break;
						default:
							dPath = new Draw_Path(getApplicationContext());
							addContentView(dPath, lParams);
							break;
						}
					}
				});  
		        builder.create().show();  
			}
		});
        
    }

    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
