/* swiper 상세보기 */
var swiper = new Swiper('.detail', {
    slidesPerView: 4,
    spaceBetween: 20,
    slidesPerGroup: 4,
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
  });
  
  var swiper = new Swiper('.video', {
      slidesPerView: 3,
    spaceBetween: 120,
      loop : true, // 무한 반복
      autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
  });
  
  
  /* swiper 메인 */
  var swiper = new Swiper('.main_class', {
      loop : true, // 무한 반복
      autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
      pagination: {
      el: '.swiper-pagination',
      type: 'fraction',
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
  });
  
  var swiper = new Swiper('.main_visual01', {
      loopFillGroupWithBlank : true,
      spaceBetween : 0, // 슬라이드간 간격
      loop : true, // 무한 반복
      autoplay: {
      delay: 6000,
      disableOnInteraction: false,
    },
  
      pagination: {
      el: '.swiper-pagination',
      type: 'fraction',
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
  });
  
  
  $('.swiper-start').on('click',function(){
          swiper.autoplay.start();
          return false;
  })
  $('.swiper-stop').on('click',function(){
          swiper.autoplay.stop();
          return false;
  })
  
  
  
  
  
  
  /* 관련제품 이미지 오버버튼 */
  $(".item01 li").bind("mouseenter focusin", function() {
      $(this).addClass("on");
      $(this).siblings().removeClass("on");
  });
  $(".item01 li").bind("mouseleave focusout", function() {
      $(this).removeClass("on");
  });
  
  
  
  
  // 레이어 팝업 : 접근성 관련 포커스 강제 이동
  function accessibilityFocus() {
    
    $(document).on('keydown', '[data-focus-prev], [data-focus-next]', function(e){
      var next = $(e.target).attr('data-focus-next'),
          prev = $(e.target).attr('data-focus-prev'),
          target = next || prev || false;
      
      if(!target || e.keyCode != 9) {
        return;
      }
      
      if( (!e.shiftKey && !!next) || (e.shiftKey && !!prev) ) {
        setTimeout(function(){
          $('[data-focus="' + target + '"]').focus();
        }, 1);
      }
      
    });
    
  }
  
  function tooltip() {
    var openBtn = '[data-tooltip]',
        closeBtn = '.tooltip-close';
    
    function getTarget(t) {
      return $(t).attr('data-tooltip');
    }
    
    function open(t) {
      var showTarget = $('[data-tooltip-con="' + t + '"]');
      showTarget.show().focus();
      showTarget.find('.tooltip-close').data('activeTarget', t);
    }
    
    function close(t) {
      var activeTarget = $('[data-tooltip-con="' + t + '"]');
      activeTarget.hide();
      $('[data-tooltip="' + t + '"]').focus();
    }
    
    $(document)
      .on('click', openBtn, function(e){
        e.preventDefault();
        open(getTarget(e.target));
      })
      .on('click', closeBtn, function(e) {
        e.preventDefault();
        close($(this).data('activeTarget'));
      })
    
  }
  
  
  $(document).ready(function(){
    
    tooltip();
    accessibilityFocus();
    
  });
  
  
  function copy_trackback(address) {
      var IE=(document.all)?true:false;
      if (IE) {
          if(confirm("이 글의 주소를 클립보드에 복사하시겠습니까?"))
              window.clipboardData.setData("Text", address);
      } else {
          temp = prompt("이 글의 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", address);
      }
  }