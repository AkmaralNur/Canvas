package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class HouseView extends View {
    private Paint paint;

    public HouseView(Context context) {
        super(context);
        init();
    }

    public HouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true); // Сглаживание
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Получаем ширину и высоту экрана
        int width = getWidth();
        int height = getHeight();

        // Размеры дома
        int houseWidth = 300;  // ширина дома
        int houseHeight = 250; // высота дома

        // Позиция дома (центрируем по горизонтали)
        int houseLeft = (width - houseWidth) / 2;
        int houseTop = height / 2 - houseHeight / 2;
        int houseRight = houseLeft + houseWidth;
        int houseBottom = houseTop + houseHeight;

        // Рисуем фон
        canvas.drawColor(Color.WHITE);

        // Рисуем землю
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 1200, width, height, paint);

        // Рисуем дом (центрирован по горизонтали)
        paint.setColor(Color.rgb(139, 69, 19)); // Коричневый цвет дома
        canvas.drawRect(houseLeft, houseTop, houseRight, houseBottom, paint);

        // Рисуем крышу
        paint.setColor(Color.rgb(139, 39, 19));
        Path roof = new Path();
        roof.moveTo(houseLeft, houseTop);                // Левая точка
        roof.lineTo((houseLeft + houseRight) / 2, houseTop - 100); // Верхняя точка
        roof.lineTo(houseRight, houseTop);               // Правая точка
        roof.close();
        canvas.drawPath(roof, paint);

        // Рисуем окно (центрированное на доме)
        paint.setColor(Color.DKGRAY);
        int windowSize = 50;
        canvas.drawRect(houseLeft + 50, houseTop + 50, houseLeft + 50 + windowSize, houseTop + 50 + windowSize, paint);

        // Рисуем дверь
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(houseRight - 100, houseBottom - 100, houseRight - 50, houseBottom, paint);

        // Рисуем узоры на окнах и дверях с использованием циклов
        paint.setColor(Color.BLACK);
        drawPatterns(canvas, houseLeft + 50, houseTop + 50, windowSize, windowSize); // Окно
        drawPatterns(canvas, houseRight - 100, houseBottom - 100, 50, 100); // Дверь

        // Рисуем пять деревьев: одно справа и четыре по бокам

        // Дерево справа
        drawTree(canvas, houseRight + 100, houseBottom - 200);

        // Дерево слева 1
        drawTree(canvas, houseLeft - 200, houseBottom - 200);

        // Дерево слева 2
        drawTree(canvas, houseLeft - 350, houseBottom - 200);

        // Дерево справа 1
        drawTree(canvas, houseRight + 250, houseBottom - 200);

        // Дерево справа 2
        drawTree(canvas, houseRight + 400, houseBottom - 200);

        // Рисуем солнце по центру
        int sunRadius = 50;
        int sunX = width / 2;  // По горизонтали центр экрана
        int sunY = 150;        // Немного выше дома
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(sunX, sunY, sunRadius, paint);

        // Рисуем лучи солнца
        paint.setColor(Color.YELLOW);
        for (int i = 0; i < 8; i++) {
            canvas.drawLine(sunX, sunY, (float) (sunX + 80 * Math.cos(i * Math.PI / 4)),
                    (float) (sunY + 80 * Math.sin(i * Math.PI / 4)), paint);
        }

        // Добавляем облака
        drawCloud(canvas, 150, 200);
        drawCloud(canvas, width - 350, 250);
        drawCloud(canvas, width / 2 - 100, 300);
    }

    // Метод для рисования узоров на окнах и дверях с помощью циклов
    private void drawPatterns(Canvas canvas, int left, int top, int width, int height) {
        paint.setColor(Color.BLACK);
        for (int i = 0; i < width; i += 10) {
            canvas.drawLine(left + i, top, left + i, top + height, paint); // Вертикальные линии
        }
        for (int i = 0; i < height; i += 10) {
            canvas.drawLine(left, top + i, left + width, top + i, paint); // Горизонтальные линии
        }
    }

    // Метод для рисования дерева
    private void drawTree(Canvas canvas, int x, int y) {
        paint.setColor(Color.rgb(34, 139, 34)); // Зеленый цвет дерева
        canvas.drawOval(x, y, x + 100, y + 200, paint); // Листва
        paint.setColor(Color.rgb(139, 69, 19)); // Ствол дерева
        canvas.drawRect(x + 45, y + 200, x + 55, y + 300, paint);
    }

    // Метод для рисования облаков
    private void drawCloud(Canvas canvas, int x, int y) {
        paint.setColor(Color.LTGRAY); // Цвет облаков (можно поменять на белый)
        canvas.drawOval(x, y, x + 100, y + 50, paint);
        canvas.drawOval(x + 30, y - 20, x + 130, y + 30, paint);
        canvas.drawOval(x + 60, y, x + 160, y + 50, paint);
    }
}
