package com.example.shenghuotong.huatu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

/*
 * ��Բ
 * 
 * �����϶�Ҳ�ã�����Ҳ�ã���ʵ�������»�Բ��ֻ�ǲ��ı�ĳЩ���Ե�����£��������������ƶ���
 */
public class Draw_Circle extends MyDraw {

	private Point rDotsPoint;// Բ��
	private int radius = 0;// �뾶
	private Double dtance = 0.0;// ��ǰ�㵽Բ�ĵľ���

	
	
	
	
	public Draw_Circle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		rDotsPoint = new Point();
	}

	
	
	
	
	// �����¼�
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {

		
		
		case MotionEvent.ACTION_DOWN:
			// ��ȡ�������
			downPoint.set((int) event.getX(), (int) event.getY());

			if (radius != 0) {
				//���㵱ǰ������ĵ㵽Բ�ĵľ���
				dtance = Math.sqrt((downPoint.x - rDotsPoint.x)
						* (downPoint.x - rDotsPoint.x)
						+ (downPoint.y - rDotsPoint.y)
						* (downPoint.y - rDotsPoint.y));
				// �������뾶��20�ͼ�20����Χ�ڣ�����Ϊ�û������Բ��
				if (dtance >= radius - 20 && dtance <= radius + 20) {
					Log.i("���-----", "��Բ��");
					downState = 1;
				//�������С�ڰ뾶������Ϊ�û������Բ��
				} else if (dtance < radius) {
					Log.i("���-----", "��Բ��");
					downState = -1;
				// ��԰��
				} else if (dtance > radius) {
					Log.i("ACTION_DOWN-----", "��Բ��");
					downState = 0;
				}
			} else {
				downState = 0;
			}
			break;

			
			
		case MotionEvent.ACTION_MOVE:
			// ��ȡ��ǰ�϶�������
			movePoint.set((int) event.getX(), (int) event.getY());

			switch (downState) {
			case 1:
				//��Բ�ϣ�Բ�Ĳ��䣬���¼���뾶
				radius = (int) Math.sqrt((movePoint.x - rDotsPoint.x)
						* (movePoint.x - rDotsPoint.x)
						+ (movePoint.y - rDotsPoint.y)
						* (movePoint.y - rDotsPoint.y));
				break;
				
			case -1:
				//��԰�ڡ��뾶���䣬�ı���Բ������
				rDotsPoint.x = movePoint.x;
				rDotsPoint.y = movePoint.y;
				break;

			default:
				//��԰�⡣��������Բ�����ꡢ�뾶����Բ
				rDotsPoint.set(downPoint.x, downPoint.y);
				radius = (int) Math.sqrt((movePoint.x - rDotsPoint.x)
						* (movePoint.x - rDotsPoint.x)
						+ (movePoint.y - rDotsPoint.y)
						* (movePoint.y - rDotsPoint.y));
				break;
				
			}
			
			/*
			 * �϶������в�ͣ�Ľ�bitmap����ɫ����Ϊ͸��
			 * ��Ȼ�������϶����̵Ĺ켣��������
			 */
			bitmap.eraseColor(Color.TRANSPARENT);
			//����Բ�ĺͰ뾶���»�Բ
			canvas.drawCircle(rDotsPoint.x, rDotsPoint.y, radius, paint);
			invalidate();
			
			
		}
		return true;
	}

}
