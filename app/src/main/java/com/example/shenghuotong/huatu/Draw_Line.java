package com.example.shenghuotong.huatu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

/*
 * ���߶�
 * 
 * �쳤�����̡��ƶ���ʵ�����ػ�ֱ��
 */
public class Draw_Line extends MyDraw {
	
	
	private Point cenPoint;//�����߶ε��м�㣬�϶�ֱ����Ҫ�õ��ε�
	private Point lPoint1,lPoint2;//�����߶ε������˵�
	private Rect lPoint1Rect,lPoint2Rect;//���߶ε������˵�Ϊ���ĵ���������
	
	public Draw_Line(Context context){
		super(context);
		// TODO Auto-generated constructor stub
		  //ʵ����
		  cenPoint = new Point();
		  lPoint1 = new Point();
		  lPoint2 = new Point();
		  lPoint1Rect = new Rect();
		  lPoint2Rect = new Rect();
	}

	
	
	
	/*
	 * (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 * �����û�������ĵ����߶εĹ�ϵ����ִ���ӳ������̡��ƶ������ػ�
	 */
	 @Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			// ��ȡ�������
			downPoint.set((int) event.getX(), (int) event.getY());
			
			//����û�����ĵĵ��� �߶������η�Χ�ڣ�����Ϊ�û���������
			if (lPoint1Rect.contains(downPoint.x, downPoint.y)) {
				downState = 1;
			//����û�����ĵĵ��� �߶��յ���η�Χ�ڣ�����Ϊ�û�������յ�
			} else if (lPoint2Rect.contains(downPoint.x, downPoint.y)) {
				downState = 2;
			//�ж��û�������ĵ��Ƿ���ֱ����
			} else if (panduan()) {
				downState = 3;
			//��ֱ����
			} else {
				downState = 0;
			}
			break;

			
			
		case MotionEvent.ACTION_MOVE:
			// ��ȡ��ǰ�϶�������
			movePoint.set((int) event.getX(), (int) event.getY());
			
			switch (downState) {
			case 1:
				//����϶�ֱ����㣬��ֱ�ߵ��յ㲻��
				lPoint1.set(movePoint.x, movePoint.y);
				moveState = 1;
				break;
			case 2:
				//����϶�ֱ���յ㣬��ֱ�ߵ���㲻��
				lPoint2.set(movePoint.x, movePoint.y);
				break;
			case 3:
				//����ֱ�ߵ��м��
				cenPoint.x = (lPoint2.x + lPoint1.x) / 2;
				cenPoint.y = (lPoint2.y + lPoint1.y) / 2;
				//�ƶ�����
				int movedX = movePoint.x - cenPoint.x;
				int movedY = movePoint.y - cenPoint.y;
				//�����趨�߶ε������˵�
				lPoint1.x = lPoint1.x + movedX;
				lPoint1.y = lPoint1.y + movedY;
				lPoint2.x = lPoint2.x + movedX;
				lPoint2.y = lPoint2.y + movedY;
				break;
			default:
				lPoint1.set(downPoint.x, downPoint.y);
				lPoint2.set(movePoint.x, movePoint.y);
				break;
			}
			
			/*
			 * �϶������в�ͣ�Ľ�bitmap����ɫ����Ϊ͸��
			 * ��Ȼ�������϶����̵Ĺ켣��������
			 */
			bitmap.eraseColor(Color.TRANSPARENT);
			//�����߶ε������˵㻭ֱ��
			canvas.drawLine(lPoint1.x, lPoint1.y, lPoint2.x, lPoint2.y,paint);
			invalidate();
			break;

			
			
			
		case MotionEvent.ACTION_UP:
			//�����趨���߶������˵�Ϊ���ĵ���������
			lPoint1Rect.set(lPoint1.x - 25, lPoint1.y - 25, lPoint1.x + 25,
					lPoint1.y + 25);
			lPoint2Rect.set(lPoint2.x - 25, lPoint2.y - 25, lPoint2.x + 25,
					lPoint2.y + 25);
			Log.i("ACTION_UP", "ACTION_UP``````````````break");
			break;

		default:
			break;
		}
		return true;
	}
	 
	 
	 
	 /*
	  * �жϵ�ǰ������ĵ��Ƿ���ֱ����
	  * 
	  * �����û�������ĵ㵽�߶������˵�ľ���֮��
	  * ���߶εľ�����бȽ� ���ж�
	  */
	 public boolean panduan() {
		 
		 double lDis = Math.sqrt((lPoint1.x-lPoint2.x)*(lPoint1.x-lPoint2.x)
				 + (lPoint1.y-lPoint2.y)*(lPoint1.y-lPoint2.y));
		 
		 double lDis1 = Math.sqrt((downPoint.x-lPoint1.x)*(downPoint.x-lPoint1.x)
				 + (downPoint.y-lPoint1.y)*(downPoint.y-lPoint1.y));
		 double lDis2 = Math.sqrt((downPoint.x-lPoint2.x)*(downPoint.x-lPoint2.x)
				 + (downPoint.y-lPoint2.y)*(downPoint.y-lPoint2.y));
		 
		 
		 if (lDis1+lDis2 >= lDis+0.00f && lDis1+lDis2 <= lDis+5.00f) {
			return true;
		}else {
			return false;
		}
	}
	 
}
