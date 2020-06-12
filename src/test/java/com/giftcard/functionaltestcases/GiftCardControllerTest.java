package com.giftcard.functionaltestcases;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.giftcard.controller.GiftCardController;
import com.giftcard.dtos.GiftCardOrderDetailsDTO;
import com.giftcard.services.GiftCardOrderService;
import com.giftcard.utilityclasses.MasterData;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GiftCardControllerTest {

	static {
		File file = new File("output_revised.txt");
		if (file.exists()) {
			try {
				FileUtils.forceDelete(new File("output_revised.txt"));
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

	@Test
	public void saveGiftCardDetails() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(giftCardOrderService.saveGiftCardOrderDetail(MasterData.getDetails())).thenReturn(Boolean.TRUE);

		ResponseEntity<Boolean> responseEntity = giftCardController.addGiftOrder(MasterData.getDetails());

		File file = new File("output_revised.txt");

		FileUtils.write(file, "\nsaveGiftCardDetails=" + (responseEntity.getBody().equals(Boolean.TRUE) ? true : false),
				true);

	}

	@Test
	public void failedTosaveGiftCardDetails() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(giftCardOrderService.saveGiftCardOrderDetail(MasterData.getDetails())).thenReturn(false);

		ResponseEntity<Boolean> responseEntity = giftCardController.addGiftOrder(MasterData.getDetails());

		File file = new File("output_revised.txt");

		FileUtils.write(file,
				"\nfailedTosaveGiftCardDetails=" + (responseEntity.getStatusCode().value() == 500 ? true : false),
				true);
	}

	@Test
	public void testFindAll() throws IOException {
		// given
		List<GiftCardOrderDetailsDTO> giftCardDetails = Arrays.asList(MasterData.getDetails(), MasterData.getDetails());

		when(giftCardOrderService.getAllOrderDetails()).thenReturn(giftCardDetails);

		// when
		ResponseEntity<List<GiftCardOrderDetailsDTO>> result = giftCardController.viewAllOrders();

		// then
		File file = new File("output_revised.txt");

		FileUtils.write(file, "\ntestFindAll=" + (result.getBody().size() == 2 ? true : false), true);

	}

	@Test
	public void testFindAllEmpty() throws IOException {
		// given
		List<GiftCardOrderDetailsDTO> giftCardDetails = new ArrayList<>();

		when(giftCardOrderService.getAllOrderDetails()).thenReturn(giftCardDetails);

		// when
		ResponseEntity<List<GiftCardOrderDetailsDTO>> result = giftCardController.viewAllOrders();

		// then
		File file = new File("output_revised.txt");

		FileUtils.write(file, "\ntestFindAllEmpty=" + (result.getBody().size() == 0 ? true : false), true);

	}

}