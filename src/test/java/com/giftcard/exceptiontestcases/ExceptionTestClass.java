package com.giftcard.exceptiontestcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.giftcard.controller.GiftCardController;
import com.giftcard.dtos.GiftCardOrderDetailsDTO;
import com.giftcard.services.GiftCardOrderService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExceptionTestClass {
	
	static {
		File file = new File("output_exception_revised.txt");
		if (file.exists()) {
			try {
				FileUtils.forceDelete(new File("output_exception_revised.txt"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	@InjectMocks
	GiftCardController giftCardController;

	@Mock
	GiftCardOrderService giftCardOrderService;

	@Test(expected = NullPointerException.class)
	public void saveGiftCardDetails() throws IOException {
		try {
			GiftCardOrderDetailsDTO orderDetails = new GiftCardOrderDetailsDTO();
			orderDetails.setBuyerFirstName("buyerFirstName");
			orderDetails.setBuyerLastName("buyerLastName");
			orderDetails.setEmail("email@gmail.com");
			double amount = 100.50;
			orderDetails.setGiftAmount((long) amount);
			orderDetails.setPhoneNumber("9010893970");
			orderDetails.setRecipienLastName("recepientName");
			orderDetails.setRecipientFirstName("recipientFirstName");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			File file = new File("output_exception_revised.txt");
			FileUtils.write(file, "\nsaveGiftCardDetails=true", true);
			throw e;
		} catch (Exception e) {
			File file = new File("output_exception_revised.txt");
			FileUtils.write(file, "\nsaveGiftCardDetails=false", true);
			throw e;
		}
		File file = new File("output_exception_revised.txt");
		FileUtils.write(file, "\nsaveGiftCardDetails=false", true);
	}
}
