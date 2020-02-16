$(document).ready(function(){
    // send message text
    $(document).ready(function(){
                
                ///send single
                $('#submitMessage').click(function(){
                    var value1 = document.getElementById('friendNameShow').innerHTML;
                    console.log("gui toi :"+ value1);
                    if($('#image').attr('src').toString() === '#') {
                        
                        var value = document.getElementById('friendNameShow').innerHTML;
                        
                        send(value);
                    } else {
                        var img2 = $("#image").attr('src').toString();
                        alert(img2);
                        var to = document.getElementById('friendNameShow').innerHTML;
                        console.log(to);
                        sendI(img2, to);
                        $('#image').attr('src','#');
                        $('#imageSend').addClass("hiddenForm");
                    }
                })                
                
                $("#message").keypress(function (e) {
                if(e.which == 13 && !e.shiftKey) {        
                    var value = document.getElementById('friendNameShow').innerHTML;
                    send(value);
                    e.preventDefault();
                }
                });
                
                ///send group
                $('#submitMessageGroup').click(function(){
                    var value1 = document.getElementById('groupNameShow').innerHTML;
                    console.log("gui toi :"+ value1);
                    if($('#imageGroup').attr('src').toString() === '#') {
                        
                        var value = document.getElementById('groupNameShow').innerHTML;
                        
                        sendGroup(value);
                    } else {
                        var img2 = $("#imageGroup").attr('src').toString();
                        var to = document.getElementById('groupNameShow').innerHTML;
                        console.log(to);
                        sendIGroup(img2, to);
                        $('#imageGroup').attr('src','#');
                        $('#imageSendGroup').addClass("hiddenForm");
                    }
                })                
                
                $("#messageGroup").keypress(function (e) {
                if(e.which == 13 && !e.shiftKey) {        
                    var value = document.getElementById('groupNameShow').innerHTML;
                    sendGroup(value);
                    e.preventDefault();
                }
                });
                
                })
                
                
                
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    // send image 
    
    
    ///send single
    //load image single
    function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#image').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
                $('#imageSend').removeClass('hiddenForm');
            }
        }

    $("#imageInput").change(function(){
        readURL(this);       
    });
    
    ///load imge group
    function readURLGroup(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#imageGroup').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
                $('#imageSendGroup').removeClass('hiddenForm');
            }
        }

    $("#imageInputGroup").change(function(){
        readURLGroup(this);       
    });
    
    // send function
    function sendI(image, to){
        var length = image.length;
        var lengthOneSend = 8000;
        var numOfSend = Math.floor(length/lengthOneSend);
        console.log(numOfSend);
        var count = 1;
        while (count != (numOfSend+1)) {
            var str = image.slice((count-1)*lengthOneSend, count*lengthOneSend)
            var json = JSON.stringify({
                "from":username,
                "to":to,
                "content":str,
                "type":"image",
                "typeMessage":"single"
                });
            ws.send(json);
            console.log(count);
            count += 1;
        }
        var strFinal = image.slice((count-1)*lengthOneSend, length);
        var json = JSON.stringify({
            "from":username,
            "to":to,
            "content": strFinal,
            "type":"imageDone",
            "typeMessage":"single"
            });
        ws.send(json);                
    }
    $('#sendImage').click(function(){
        var img2 = $("#image").attr('src').toString();
        var to = document.getElementById('friendNameShow').innerHTML;
        console.log(to);
        sendI(img2, to);
        $('#imageSend').addClass("hiddenForm");
    })
    
    /////////send group
    function sendIGroup(image, to){
        var length = image.length;
        var lengthOneSend = 8000;
        var numOfSend = Math.floor(length/lengthOneSend);
        console.log(numOfSend);
        var count = 1;
        while (count != (numOfSend+1)) {
            var str = image.slice((count-1)*lengthOneSend, count*lengthOneSend)
            var json = JSON.stringify({
                "from":username,
                "to":to,
                "content":str,
                "type":"image",
                "typeMessage":"group"
                });
            ws.send(json);
            console.log(count);
            count += 1;
        }
        var strFinal = image.slice((count-1)*lengthOneSend, length);
        var json = JSON.stringify({
            "from":username,
            "to":to,
            "content": strFinal,
            "type":"imageDone",
            "typeMessage":"group"
            });
        ws.send(json);                
    }
    $('#sendImageGroup').click(function(){
        var img2 = $("#image").attr('src').toString();
        var to = document.getElementById('friendNameShow').innerHTML;
        console.log(to);
        sendI(img2, to);
        $('#imageSend').addClass("hiddenForm");
    })
})