<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Calender</title>

<!-- **********************************Start CSS Files*************************************** -->

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style-public.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-datepicker.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/datatable/buttons.dataTables.min.css"/>" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/font-poppins-opne-sans-mont.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/datatable/dataTables.bootstrap4.min.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/datatable/data-tableresponsive.bootstrap4.min.css"/>" type="text/css"/>

<!-- **********************************End CSS Files*************************************** -->

<!-- **********************************Start JS Files*************************************** -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datatable/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datatable/dataTables.bootstrap4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datatable/dataTables.responsive.min.js"/>"></script>

<!-- **********************************End JS Files*************************************** -->



</head>

<body>
<h2>This is Event Calender Page</h2>
<br>
<font color="red">&nbsp;NOTE:Please fill all mandatory(*) fields</font>
<br>
<form:form  id="eventFormId" name="eventFormId" method="post" autocomplete="off">
<input type="hidden" id="event_id" name="hidEventId" > 

<!-- *************************Start Form for Add New Event Data*****************************-->
		
<div class="row">
	<div class="col-lg-4">
		<div class="form-group">
			<label><b>Event Name</b><font color="red">*</font></label>
			<input type="text" class="form-control" name="eventName" id="eventNm_id" onclick="hideBorder(this);" >
		</div> 
	</div>
	<div class="col-lg-4">
		<div class="form-group">
			<label><b>Start Date</b><font color="red">*</font></label>
			<input type="text" class="form-control" name="startDate" id="start_dt" placeholder="dd-mm-yyyy" onclick="hideBorder(this);" >
		</div> 
	</div>
	<div class="col-lg-4">
		<div class="form-group">
			<label><b>End Date</b><font color="red">*</font></label>
			<input type="text" class="form-control" name="endDate" id="end_dt" placeholder="dd-mm-yyyy" onclick="hideBorder(this);" >
		</div> 
	</div>
</div>
<div class="row">
	<div class="col-lg-4">
		<div class="form-group">
			<label><b>Start Time</b><font color="red">*</font></label>
			<input type="text" class="form-control" name="startTime" id="start_time" onclick="hideBorder(this);" >
		</div> 
	</div>
	<div class="col-lg-4">
		<div class="form-group">
			<label><b>End Time</b><font color="red">*</font></label>
			<input type="text" class="form-control" name="endTime" id="end_time" onclick="hideBorder(this);" >
		</div> 
	</div>
	<div class="col-lg-4">
		<div class="form-group">
			<label><b>DOW</b><font color="red">*</font></label>
			<select id="dow_id" name="dow" class="form-control" onclick="hideBorder(this);" >
				<option value="Weekdays">Weekdays</option>
				<option value="Weekend">Weekend</option>
				<option value="Both">Weekdays and Weekend(Both)</option>
			</select>
		</div> 
	</div>
</div>
<div class="row">
<div class="col-lg-12">
<input type="button" class="btn btn-primary" value="SUBMIT" onclick="submitEven();"/>
</div>
</div>
<!-- *************************End Form for Add New Event Data*****************************-->
<br><br>
<h3>List of Events</h3>

<!-- *************************Start Show List of Event Data*****************************-->

<div class="row">
    <div class="tile">
		<div class="tile-header">
		</div>
		<div class="tile-body">
			
				<div class="form-group">
					<div class="table-responsive">
						<table  id="dataList" class="table-bordered table table-striped">
							<thead>
								<th>SR NO</th>
								<th>EVENT NAME</th>
								<th>START DATE</th>
								<th>END DATE</th>
								<th>START TIME</th>
								<th>END TIME</th>
								<th>DOW</th>
								<th>ACTION</th>
<!-- 								<th>EVENT DATE</th> -->
								
								<%-- <th><fmt:message bundle="${UniversityLables}" key="ACTION"/></th> --%>
							</thead>
							<tbody>
								<c:forEach items="${eventList}" var="i" varStatus="c">
								<tr>
									<td>${c.count}</td>									
									<td>${i.eventName}</td>
									<td>${i.startDate}</td>
									<td>${i.endDate}</td>
									<td>${i.startTime}</td>
									<td>${i.endTime}</td>
									<td>${i.dow}</td>
									<td><div class="d-flex"> <input type="button"  class="form-control mr-2" onclick="getSubEventData('${i.eventId}');" value="VIEW">
										<input type="button"  class="form-control" onclick="deleteEventData('${i.eventId}');" value="DELETE">
									</div></td>
									
								</tr>
								</c:forEach>							
								
							</tbody>
						</table>
					</div>
				</div>
			
		</div>
	</div>
</div>
<!-- *************************End Show List of Event Data*****************************-->

<!-- *************************Start Open Model for List of Sub Event Data*****************************-->
<div class="modal subevent"  id="subEventModelId">
	<div class="modal-dialog"  >
		<div class="modal-content rounded">
        	<div class="modal-header">
      				<p class="mb-0">Event Calender</p>
		            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
          	</div>
            <div class="modal-body">
 				<form:form name="subEventDataForm" id="subEventDataFormId" >
						<table  id="subEventDataList" class="table-bordered table table-striped">
							<thead>								
								<th>EVENT NAME</th>
								<th>START DATE</th>
								<th>END DATE</th>
								<th>START TIME</th>
								<th>END TIME</th>
								<th>DOW</th>								
							</thead>
							<tbody>
								
							</tbody>
						</table>
                </form:form>
            </div>             
        </div>
    </div>
</div>

<!-- *************************End Open Model for List of Sub Event Data*****************************-->

<button class='btn btn-sm btn-success' id="openEventModelId" target='_blank' data-toggle="modal" data-target=".subevent" style="display:none; "></button>



</form:form>
</body>
<script type = "text/javascript">

// *************Start For Validation of input box and select tag******************
function validationFunction(array) {
	var flag = true;	
	
	for ( var i = 0; i < array.length; i++) {
		
		if (($("#" + array[i].id).val() != null && $("#" + array[i].id).val() != "-1"
				&& $("#" + array[i].id).val() != '')) {
			
// 			alert(array[i].id);
			
			$("#" + array[i].id).css({
				'border-color' : '',
				'background-color' : ''
			});
			
		} else {
			
			$("#" + array[i].id).css({
				'border-color' : '#ef0000',
				'background-color' : '#ffd1d1'
			});
			flag = false;
		}
	}

return flag;
}
function hideBorder(obj) 
{
	$("#"+obj.id).css({
	    'border-color': '',
	    'background-color': ''});
	return true;
}
//*************End For Validation of input box and select tag******************

// *************Start For Submit Add New Event******************
function submitEven(){

	
if((document.getElementById("eventNm_id").value == "" || document.getElementById("eventNm_id").value == null) 
	&& (document.getElementById("start_dt").value == "" || document.getElementById("start_dt").value == null)
	&& (document.getElementById("end_dt").value == "" || document.getElementById("end_dt").value == null)
	&& (document.getElementById("start_time").value == "" || document.getElementById("start_time").value == null)
	&& (document.getElementById("end_time").value == "" || document.getElementById("end_time").value == null)){
	alert("Please fill all mandatory fields..!");
}else{
	if(confirm("Are You Sure ?"))
	{
		document.eventFormId.action = "../event/saveEvent";
		document.eventFormId.submit(); 
	}
}
	
}
//*************End For Submit Add New Event******************

// *************Start For Delete Event***********************
function deleteEventData(eventId){	
	if(confirm("Are You Sure ?"))
	{
		$("#event_id").val(eventId);
		document.eventFormId.action = "../event/deleteEvent";
		document.eventFormId.submit(); 
	}	
}
//*************End For Delete Event***********************

// *************Start For Fetch Sub Event Data using Ajax***********************
function getSubEventData(eventId)
{
$("#subEventDataList > tbody").empty();	
	var ServiceData = {"eventId":eventId};	
		$.ajax({
			type : "POST",
			data :  ServiceData,
			url : "${pageContext.request.contextPath}/event/getSubEventData",
			dataType : "text",
			success : function(result) 
			{			
				if(result !=null)
				{
					var data = $.parseJSON(result);

									
					$.each(data,function(key,value)
					{							
						var bodyRow=$("<tr/>");
						bodyRow.append( $("<td/>").addClass("text-left").append(value.eventName));
						bodyRow.append( $("<td/>").addClass("text-left").append(value.startDate));
						bodyRow.append( $("<td/>").addClass("text-left").append(value.endDate));
						bodyRow.append( $("<td/>").addClass("text-left").append(value.startTime));
						bodyRow.append( $("<td/>").addClass("text-left").append(value.endTime));
						bodyRow.append( $("<td/>").addClass("text-left").append(value.dow));						
						$("#subEventDataList > tbody").append(bodyRow)
					});
									
					$("#openEventModelId").trigger('click');
				}

			},
			async:false,
			error : function(xhr, status, errorThrown) {
				alert("Something Went Wrong to Connect the Database ");			
			}, 
		
		});
	
}
//*************End For Fetch Sub Event Data using Ajax***********************

// *************Start For Page Ready Function to Set Datatable Function*******
$(document).ready(function() {
    $('#dataList').DataTable();
    
});
//*************End For Page Ready Function to Set Datatable Function*********

// *************Start For Set Bootstrap Datepicker***********************
$('#start_dt').datepicker({
	format: "dd-mm-yyyy",
	autoclose: true,
	todayHighlight: true
});

$('#end_dt').datepicker({
	format: "dd-mm-yyyy",
	autoclose: true,
	todayHighlight: true
});
//*************End For Set Bootstrap Datepicker***********************


</script>
</html>