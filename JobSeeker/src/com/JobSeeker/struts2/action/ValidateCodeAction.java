package com.JobSeeker.struts2.action;

import com.opensymphony.xwork2.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.http.*;

import org.apache.struts2.interceptor.*;

public class ValidateCodeAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware {
	private HttpServletRequest request;

	private HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	private String codeChars = "%#23456789abcdefghkmnpqrstuvwxyzABCDEFGHKLMNPQRSTUVWXYZ";

	private Color getRandomColor(int minColor, int maxColor) {
		Random random = new Random();
		if (minColor > 255)
			minColor = 255;
		if (maxColor > 255)
			maxColor = 255;
		int red = minColor + random.nextInt(maxColor - minColor);
		int green = minColor + random.nextInt(maxColor - minColor);
		int blue = minColor + random.nextInt(maxColor - minColor);
		return new Color(red, green, blue);
	}

	@Override
	public String execute() throws Exception {
		int charsLength = codeChars.length();

		response.setHeader("ragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		int width = 90, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandomColor(180, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, height));

		g.setColor(getRandomColor(120, 180));
		StringBuilder validationCode = new StringBuilder();
		String[] fontNames = { "Times New Roman", "Book antiqua", "Arial" };
		for (int i = 0; i < 3 + random.nextInt(3); i++) {
			g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC,
					height));
			char codeChar = codeChars.charAt(random.nextInt(charsLength));
			validationCode.append(codeChar);
			g.setColor(getRandomColor(10, 100));
			g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7),
					height - random.nextInt(6));

		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(5 * 60);
		session.setAttribute("validation_code", validationCode.toString());

		g.dispose();
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "JPEG", os);

		return null;
	}

}