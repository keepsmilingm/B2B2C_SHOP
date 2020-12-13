function tishi(content,url) {
    jQuery(".xiaoxi").remove();
    var html = '<div class="xiaoxi none" id="msg" style="z-index:9999;left: 25%;width: 50%;position: fixed;' +
        'background:none;top:90%;"> <p class="msg" style="background: none repeat scroll 0 0 rgba(0,0,0,0.98); ' +
        'border-radius: 3rem;color: #fff; margin: 0 auto;padding: 0.8rem;text-align: center;width: 70%;opacity: 0.9;"></p></div>';
    jQuery(document.body).append(html);
    jQuery("#msg").show();
    jQuery(".msg").html(content);
    if(url){
        window.setTimeout("location.href='"+url+"'", 2000);
    }else{
        setTimeout('jQuery("#msg").fadeOut()', 1500);
    }
}