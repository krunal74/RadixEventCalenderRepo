package com.radix.event.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radix.event.model.MstEventBo;
import com.radix.event.model.TrnEventBo;
import com.radix.event.repository.MstEventRepository;
import com.radix.event.repository.TrnEventBoRepository;


/**
 * @author Krunal Prajapati
 *
 */
@Service
@Transactional
public class EventServices {

	@Autowired
	private MstEventRepository mstEventRepository;
	@Autowired
	private TrnEventBoRepository trnEventBoRepository;
	
	private static final DateTimeFormatter DATE_FORMAT_WITH_DASH = DateTimeFormatter.ofPattern("dd-MM-yyyy");	
		
	/**
	 * @author Krunal Prajapati
	 * @param map
	 * @return List of MstEvent
	 */
	public List<MstEventBo> getEventData(Map<String, Object> map){
		return mstEventRepository.findAll();
		
	}
	
	/**
	 * @author Krunal Prajapati
	 * @param map
	 * @return Object of MstEvent
	 * @throws Exception
	 */
	@Transactional	
	public MstEventBo saveEventData(Map<String, Object> map) throws Exception{
		
		MstEventBo mstEventBo=(MstEventBo) map.get("mstEventBo");
		HttpServletRequest request=(HttpServletRequest) map.get("request");
//	*******************************Start Save Event Data in MST_EVENT Table********************************************  
		mstEventBo.setCrtIp(request.getRemoteAddr());
		mstEventBo.setCrtUser("ADMIN");
		mstEventBo.setStatus("1");
		
		MstEventBo eventBo=mstEventRepository.save(mstEventBo);
//	*******************************End Save Event Data in MST_EVENT Table********************************************  
		if(null != eventBo) {
			if(null != eventBo.getEventId() && !eventBo.getEventId().equals("")) {
				
				if(null != eventBo.getDow() && !eventBo.getDow().equals("")) {
					
//	************************************Start Logic of Split Dates Between StartDate and EndDate*********************** 
					LocalDate startDate = LocalDate.parse(eventBo.getStartDate(), DATE_FORMAT_WITH_DASH);
					LocalDate endDate = LocalDate.parse(eventBo.getEndDate(), DATE_FORMAT_WITH_DASH);
					
					LocalDate weekDays=startDate;
					
					List<TrnEventBo> trnEventList=new ArrayList<TrnEventBo>();
					TrnEventBo trnEventBo=new TrnEventBo();
					
					StringBuffer strDow=new StringBuffer();
//	************************************Start Logic of Week as per the StartDate*********************** 					
					if(eventBo.getDow().equalsIgnoreCase("Weekdays")) {
						if(weekDays.getDayOfWeek().equals(DayOfWeek.SATURDAY) || weekDays.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
							weekDays=weekDays.plusWeeks(1).with(DayOfWeek.MONDAY);
						}
						
					}else if(eventBo.getDow().equalsIgnoreCase("Weekend")) {					
						if(weekDays.getDayOfWeek().equals(DayOfWeek.MONDAY) || weekDays.getDayOfWeek().equals(DayOfWeek.TUESDAY) 
						|| weekDays.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || weekDays.getDayOfWeek().equals(DayOfWeek.THURSDAY)
						|| weekDays.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
							weekDays=weekDays.with(DayOfWeek.SATURDAY);
						}
						
					}
//	************************************End Logic of Week as per the StartDate********************************************
					
//	************************************Start Logic of iterate loop until EndDate********************************************
					while(weekDays.isBefore(endDate) || weekDays.isEqual(endDate)) {					
						if(trnEventBo.getStartDate() == null || trnEventBo.getStartDate().equals("")) {
							trnEventBo.setStartDate(weekDays.format(DATE_FORMAT_WITH_DASH));
							trnEventBo.setEventId(eventBo.getEventId());
							trnEventBo.setEventName(eventBo.getEventName());
							trnEventBo.setStartTime(eventBo.getStartTime());
							trnEventBo.setEndTime(eventBo.getEndTime());
							trnEventBo.setDow(eventBo.getDow());
							trnEventBo.setCrtIp(request.getRemoteAddr());
							trnEventBo.setCrtUser(eventBo.getCrtUser());
							trnEventBo.setStatus("1");						

						}						
						if(eventBo.getDow().equalsIgnoreCase("Weekdays")) {
							strDow.append(weekDays.getDayOfWeek().getDisplayName(TextStyle.NARROW,Locale.UK)+" ");
							if(weekDays.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
								if(trnEventBo.getEndDate() == null || trnEventBo.getEndDate().equals("")) {
									trnEventBo.setEndDate(weekDays.format(DATE_FORMAT_WITH_DASH));
									trnEventBo.setDow(strDow.toString());
								}
								trnEventList.add(trnEventBo);
								trnEventBo=new TrnEventBo();
								strDow.setLength(0);
								weekDays=weekDays.plusDays(3);						
							}else {
								weekDays=weekDays.plusDays(1);
								
							}
						}else if(eventBo.getDow().equalsIgnoreCase("Weekend")) {
							strDow.append(weekDays.getDayOfWeek().getDisplayName(TextStyle.NARROW,Locale.UK)+" ");
							if(weekDays.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
								if(trnEventBo.getEndDate() == null || trnEventBo.getEndDate().equals("")) {
									trnEventBo.setEndDate(weekDays.format(DATE_FORMAT_WITH_DASH));
									trnEventBo.setDow(strDow.toString());
								}
								trnEventList.add(trnEventBo);
								trnEventBo=new TrnEventBo();
								strDow.setLength(0);
								weekDays=weekDays.plusDays(6);						
							}else {
								weekDays=weekDays.plusDays(1);
							}
						}else if(eventBo.getDow().equalsIgnoreCase("Both")) {
							if(weekDays.isEqual(endDate)) {
								if(trnEventBo.getEndDate() == null || trnEventBo.getEndDate().equals("")) {
									trnEventBo.setEndDate(weekDays.format(DATE_FORMAT_WITH_DASH));
								}
								trnEventList.add(trnEventBo);
								trnEventBo=new TrnEventBo();
								weekDays=weekDays.plusDays(1);
							}else {
								weekDays=weekDays.plusDays(1);
							}
						}					
											
					}	
//	************************************End Logic of iterate loop until EndDate********************************************
					if(null != trnEventBo.getStartDate() && !trnEventBo.getStartDate().equals("")) {
						trnEventBo.setEndDate(endDate.format(DATE_FORMAT_WITH_DASH));
						trnEventBo.setDow(strDow.toString());
						trnEventList.add(trnEventBo);
					}
//	*******************************Start Save Sub Event Data in TRN_EVENT Table********************************************  					
					List<TrnEventBo> trneventList=this.trnEventBoRepository.saveAll(trnEventList);
//	*******************************End Save Sub Event Data in TRN_EVENT Table********************************************  
					
//	************************************End Logic of Split Dates Between StartDate and EndDate*********************** 					
				}				
			}
		}		

		return eventBo;
		
	}
	
	/**
	 * @author Krunal Prajapati
	 * @param map
	 * @return List of TrnEvent
	 */
	public List<TrnEventBo> getSubEventData(Map<String, Object> map){		
		
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String eventId=request.getParameter("eventId");		

		return trnEventBoRepository.findByEventId(eventId);
		
	}
	
	/**
	 * @author Krunal Prajapati
	 * @param map
	 * @return 0 or 1 from delete Status from MST_EVENT
	 */
	public int deleteByEventId(Map<String, Object> map){		
		
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String eventId=request.getParameter("hidEventId");		

		return mstEventRepository.deleteByEventId(eventId);
		
	}
}
