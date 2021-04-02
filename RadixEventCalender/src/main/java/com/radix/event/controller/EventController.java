package com.radix.event.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.radix.event.model.MstEventBo;
import com.radix.event.model.TrnEventBo;
import com.radix.event.service.EventServices;

/**
 * @author Krunal Prajapati
 *
 */
@Controller
@RequestMapping(value = "/event")
public class EventController {
	
	@Autowired	
	private EventServices eventServices;
	
	
	
	/**
	 * @author Krunal Prajapati
	 * @param request
	 * @param response
	 * @return ModelAndView(Event Calender Page)
	 */
	@RequestMapping(value = "/loadEventCalender")	
	public ModelAndView loadEventCalender(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		map.put("request", request);

		
		List<MstEventBo> eventList=eventServices.getEventData(map);
		request.setAttribute("eventList", eventList);
		
		return new ModelAndView("loadEventCalender");
	}
	
	/**
	 * @author Krunal Prajapati
	 * @param request
	 * @param response
	 * @param bo
	 * @return ModelAndView(Event Calender Page)
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveEvent")	
	public ModelAndView saveEvent(HttpServletRequest request,HttpServletResponse response,@ModelAttribute MstEventBo bo) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("request", request);
		map.put("mstEventBo", bo);
		
		MstEventBo mstEventBo=eventServices.saveEventData(map);
		return this.loadEventCalender(request, response);
	}
	
	/**
	 * @author Krunal Prajapati
	 * @param request
	 * @param response
	 * @return ResponseEntity Object of TrnEvent List 
	 */
	@RequestMapping(value = "/getSubEventData",produces =MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<TrnEventBo>> getSubEventData(HttpServletRequest request,HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("request", request);
		
		List<TrnEventBo> trnEventList=eventServices.getSubEventData(map);
		request.setAttribute("trnEventList", trnEventList);

		return ResponseEntity.ok(trnEventList);
		
	}
	
	/**
	 * @author Krunal Prajapati
	 * @param request
	 * @param response
	 * @return ModelAndView(Event Calender Page)
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteEvent")	
	public ModelAndView deleteEvent(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		Map<String, Object> map = new HashMap<>();
		map.put("request", request);		
		
		int deleteEvent=eventServices.deleteByEventId(map);
				
		return this.loadEventCalender(request, response);
	}

}
