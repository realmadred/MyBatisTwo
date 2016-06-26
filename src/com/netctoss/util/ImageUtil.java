package com.netctoss.util;

import java.awt.Color;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.netctoss.exception.MyException;

import patchca.color.ColorFactory;
import patchca.color.SingleColorFactory;
import patchca.filter.predefined.CurvesRippleFilterFactory;
import patchca.font.RandomFontFactory;
import patchca.service.Captcha;
import patchca.service.CaptchaService;
import patchca.service.ConfigurableCaptchaService;
import patchca.utils.encoder.EncoderHelper;
import patchca.word.RandomWordFactory;

public class ImageUtil {
	private static final String DEFAULT_CHARACTERS = "absdegkmnopwx23456789"; // 自己设置！
	private static int DEFAULT_FONT_SIZE = 25;
	private static int DEFAULT_WORD_LENGTH = 5;
	private static int DEFAULT_WIDTH = 110;
	private static int DEFAULT_HEIGHT = 35;

	private ImageUtil() {
	}

	public static CaptchaService create(int fontSize, int wordLength,
			String characters, int width, int height) {
		ConfigurableCaptchaService service = null;
		// 字体大小设置
		RandomFontFactory ff = new RandomFontFactory();
		ff.setMinSize(fontSize);
		ff.setMaxSize(fontSize);
		// 生成的单词设置
		RandomWordFactory rwf = new RandomWordFactory();
		rwf.setCharacters(characters);
		rwf.setMinLength(wordLength);
		rwf.setMaxLength(wordLength);
		// 干扰线波纹
		CurvesRippleFilterFactory crff = new CurvesRippleFilterFactory();
		//DiffuseRippleFilterFactory drff = new DiffuseRippleFilterFactory();
		//WobbleRippleFilterFactory mrff = new WobbleRippleFilterFactory();
		//RippleFilterFactory mrff = new RippleFilterFactory();
		// 处理器
		service = new ExConfigurableCaptchaService();
		service.setFontFactory(ff);
		service.setWordFactory(rwf);
		//service.setFilterFactory(crff);
		//service.setFilterFactory(drff);
		service.setFilterFactory(crff);
		// 生成图片大小（像素）
		service.setWidth(width);
		service.setHeight(height);
		return service;
	}

	public static CaptchaService create() {
		return create(DEFAULT_FONT_SIZE, DEFAULT_WORD_LENGTH,
				DEFAULT_CHARACTERS, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	// 随机变色处理
	static class ExConfigurableCaptchaService extends
			ConfigurableCaptchaService {
		private static final Random RANDOM = new Random();
		private List<SingleColorFactory> colorList = new ArrayList<SingleColorFactory>(); // 为了性能

		public ExConfigurableCaptchaService() {
			colorList.add(new SingleColorFactory(Color.blue));
			colorList.add(new SingleColorFactory(Color.black));
			colorList.add(new SingleColorFactory(Color.red));
			colorList.add(new SingleColorFactory(Color.pink));
			colorList.add(new SingleColorFactory(Color.orange));
			colorList.add(new SingleColorFactory(Color.green));
			colorList.add(new SingleColorFactory(Color.magenta));
			colorList.add(new SingleColorFactory(Color.cyan));
		}

		public ColorFactory nextColorFactory() {
			int index = RANDOM.nextInt(colorList.size());
			return colorList.get(index);
		}

		@Override
		public Captcha getCaptcha() {
			ColorFactory cf = nextColorFactory();
			/*MarbleRippleFilterFactory drff = (MarbleRippleFilterFactory) this
					.getFilterFactory();*/
			CurvesRippleFilterFactory crff = (CurvesRippleFilterFactory) this
					.getFilterFactory();
			crff.setColorFactory(cf);
			this.setColorFactory(cf);
			return super.getCaptcha();
		}
	}

	public static String getImage(OutputStream outStream) {
		CaptchaService service = ImageUtil.create();
		try {
			String captcha = EncoderHelper.getChallangeAndWriteImage(service,
					"png", outStream);
			//System.out.println(captcha);
			return captcha;
		}catch (Exception e) {
			throw new MyException("创建图片验证码失败！",e);
		}
	}

	public static void main(String[] args) throws Exception {

	}
}