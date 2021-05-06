package com.example.shenghuotong.huatu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;

/*
 * ��Բ��
 * 
 * android�Լ�û�л�Բ���ķ�����������Ҫ�Լ�����취��
 * ˼·��
 * 1���Ȼ�һ����Բ��
 * 2�����ݵ�һ����Բ���ڶ�����Բ����������Բƽ��
 * 3����������Բ����ߺ��ұ���������������������Բ����
 */
public class Draw_Column extends MyDraw {
	
	//������Բ1��2��4��������
	private Point oval1Point1,oval1Point2,oval2Point1,oval2Point2;
	private RectF rectF1,rectF2;
	 
	public Draw_Column(Context context){
		  super(context);
		// TODO Auto-generated constructor stub
		  
		 //ʵ����
		 oval1Point1 = new Point();
		 oval1Point2 = new Point();
		 oval2Point1 = new Point();
		 oval2Point2 = new Point();
		 rectF1 = new RectF();
		 rectF2 = new RectF();
	}
	
	

	
	 //�����¼�
	 @Override
	 public boolean onTouchEvent(MotionEvent event) {
		 
		 switch (event.getAction()) {
		 case MotionEvent.ACTION_DOWN:
			//��ȡ�������
			downPoint.set((int) event.getX(), (int) event.getY());
			break;
			
			
		 case MotionEvent.ACTION_MOVE:
			//��ȡ��ǰ�϶�������
			movePoint.set((int) event.getX(), (int) event.getY());
			
			/*
			 * ����Բ�뻭��������
			 * ��ȷ��������1��Ҫ�������������oval1Point1��oval1Point2
			 */
			oval1Point1.set(downPoint.x, downPoint.y);
			oval1Point2.set(movePoint.x, movePoint.y);
			
			/*
			 * ��Բ1 ��ߺ��ұߵĵ������LeftPoint_oval1��RightPoint_oval1
			 */
			//������
			int y1 = oval1Point1.y+(oval1Point2.y-oval1Point1.y)/2;
			Point LeftPoint_oval1 = new Point(oval1Point1.x, y1);
			Point RightPoint_oval1 = new Point(oval1Point2.x, y1);
			
			//����һ����������Ҫ�ı�ľ���
			int distance = (int) Math.abs(Math.sqrt((oval1Point2.x-oval1Point1.x)*(oval1Point2.x-oval1Point1.x)
					+ (oval1Point2.y-oval1Point1.y)*(oval1Point2.y-oval1Point1.y)));
			
			
			/*
			 * ������Բ1����Բ2
			 * Ϊ������Բ1����Բ2����ƽ�У������겻�䣬�ı�������
			 * ȷ������Բ2��Ҫ������������oval2Point1��oval2Point2
			 */
			oval2Point1.set(oval1Point1.x, oval1Point1.y+distance);
			oval2Point2.set(oval1Point2.x, oval1Point2.y+distance);
			
			/*
			 * ��Բ2 ��ߺ��ұߵĵ������LeftPoint_oval1��RightPoint_oval1
			 */
			//������
			int y2 = oval2Point1.y+(oval2Point2.y-oval2Point1.y)/2;
			Point LeftPoint_oval2 = new Point(oval1Point1.x, y2);
			Point RightPoint_oval2 = new Point(oval1Point2.x, y2);
			
			rectF1.set(oval1Point1.x,oval1Point1.y,oval1Point2.x,oval1Point2.y);
			rectF2.set(oval2Point1.x,oval2Point1.y,oval2Point2.x,oval2Point2.y);
			
			bitmap.eraseColor(Color.TRANSPARENT);
			
			
			canvas.drawOval(rectF1, paint);//����Բ1
			canvas.drawOval(rectF2, paint);//����Բ2
			//�������ߣ�����Բ1����Բ2��������
			canvas.drawLine(LeftPoint_oval1.x, LeftPoint_oval1.y, LeftPoint_oval2.x, LeftPoint_oval2.y, paint);
			canvas.drawLine(RightPoint_oval1.x, RightPoint_oval1.y, RightPoint_oval2.x, RightPoint_oval2.y, paint);
			invalidate();
			break;
		
		 default:
			break;
		}

	  return true;
	 }
	 
}
