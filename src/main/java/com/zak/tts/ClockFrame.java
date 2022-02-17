package com.zak.tts;

import java.awt.*;
import java.text.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;

public class ClockFrame extends JFrame {

	private LocalDateTime now;
	DateTimeFormatter timeFormat;
	DateTimeFormatter dayFormat;
	DateTimeFormatter dateFormat;
	JLabel timeLabel;
	JLabel dayLabel;
	JLabel dateLabel;
	JButton button;
	String time;
	String day;
	String date;

	ClockFrame() {
		
		this.now = LocalDateTime.now(); 
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Speaking Clock");
		this.setLayout(new FlowLayout());
		this.setSize(350, 230);
		this.setResizable(false);

		timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		dayFormat = DateTimeFormatter.ofPattern("EEEE");
		dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		timeLabel.setForeground(new Color(0x00FF00));
		timeLabel.setBackground(Color.black);
		timeLabel.setOpaque(true);

		dayLabel = new JLabel();
		dayLabel.setFont(new Font("Ink Free", Font.PLAIN, 35));

		dateLabel = new JLabel();
		dateLabel.setFont(new Font("Ink Free", Font.PLAIN, 28));
		
		button = new JButton();
		button.setText("Tell the time");
		button.addActionListener(e -> {
			TimeToSpeech.processTimeSpeaker(now.getHour(), now.getMinute());
			//TimeToSpeech.processTimeSpeaker(0, 0);
		});

		this.add(timeLabel);
		this.add(dayLabel);
		this.add(dateLabel);
		this.add(button);
		this.setVisible(true);

		setTime();
	}

	public void setTime() {
		while (true) {
			
			time = LocalDateTime.now().format(timeFormat);
			timeLabel.setText(time);

			day = LocalDateTime.now().format(dayFormat);
			dayLabel.setText(day);

			date = LocalDateTime.now().format(dateFormat);
			dateLabel.setText(date);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
