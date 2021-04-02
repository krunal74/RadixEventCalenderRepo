(function () {
	"use strict";

	var treeviewMenu = $('.app-menu');

	// Toggle Sidebar
	$('[data-toggle="sidebar"]').click(function(event) {
		event.preventDefault();
		$('.app').toggleClass('sidenav-toggled');
	});

	// Activate sidebar treeview toggle
	$("[data-toggle='treeview']").click(function(event) {
		event.preventDefault();
		if(!$(this).parent().hasClass('is-expanded')) {
			treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');
		}
		$(this).parent().toggleClass('is-expanded');
	});

	// Set initial active toggle
	$("[data-toggle='treeview.'].is-expanded").parent().toggleClass('is-expanded');

	//Activate bootstrip tooltips
	$("[data-toggle='tooltip']").tooltip();

})();

/**********************************************/
// Acc
$(document).on("click", ".naccs .menu div", function() {
  var numberIndex = $(this).index();

  if (!$(this).is("active")) {
    $(".naccs .menu div").removeClass("active");
    $(".naccs ul li").removeClass("active");

    $(this).addClass("active");
    $(".naccs ul").find("li:eq(" + numberIndex + ")").addClass("active");

    var listItemHeight = $(".naccs ul")
      .find("li:eq(" + numberIndex + ")")
      .innerHeight();
    $(".naccs ul").height(listItemHeight + "px");
  }
});


/*===============================================================================Personal Information model*/
$(function(){
  $('[role=dialog]')
      .on('show.bs.modal', function(e) {
        $(this)
            .find('[role=document]')
                .removeClass()
                .addClass('modal-dialog ' + $(e.relatedTarget).data('ui-class'))
  })
})









 /*===============================================================================Personal Information model*/

/*==============================newmenu*/
/*   function resizeHeaderOnScroll() {
  const distanceY = window.pageYOffset || document.documentElement.scrollTop,
  shrinkOn = 200,
  headerEl = document.getElementById('js-header');
  
  if (distanceY > shrinkOn) {
    headerEl.classList.add("smaller");
  } else {
    headerEl.classList.remove("smaller");
  }
}
window.addEventListener('scroll', resizeHeaderOnScroll);

    $('#header').prepend('<div id="menu-icon"><span class="first"></span><span class="second"></span><span class="third"></span></div>');
  
  $("#menu-icon").on("click", function(){
    $("nav").slideToggle();
    $(this).toggleClass("active");
});
*/

    

  /*==============================newmenu*/
/*==========================================left-model*/
$(document).ready(function(){
  $(".mo-click-button").click(function(){
    $(".model-left").toggleClass("addmo");
  });
});
 



 /*==========================================left-model*/ 
/*==========================================date*/
$('#datepicker').datepicker({
   weekStart: 1,
       daysOfWeekHighlighted: "6,0",
       autoclose: true,
       todayHighlight: true,

            uiLibrary: 'bootstrap4'

        });
/*==========================================date*/

/*================================================collapse-dashboard*/

/*******************************
* ACCORDION WITH TOGGLE ICONS
*******************************/
 




/*================================================collapse-dashboard*/