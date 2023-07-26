$.ajaxSetup({cache:false});




$(document).ready(function(){
        
  $('.dropdown,.dropdown-menu').hover(function(){

    if($(window).width()>=768){
      $(this).addClass('open').trigger('shown.bs.dropdown', relatedTarget)
      return false;
    }
   
  },function(){
    if($(window).width()>=768){
      $(this).removeClass('open').trigger('hidden.bs.dropdown', relatedTarget)
      return false;
    }
  })
   
})



/* 하단 메뉴 스크롤 감지 이벤트*/
$(function() {
     $("#btn_top").click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 400);
            return false;
        });   
});
