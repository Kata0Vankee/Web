$(document).ready(function(){
    function writeRequest(data){
        var obj = new Array();
        data.forEach((temp) => {
            var value = temp.toString();
            obj.push(value);
        })
        console.log(obj[0]);
        if (obj[0] === "no") {
            $('#addResult').html(obj[1]);
        }
        else {
            for (i=1; i<obj.length; i++){
                console.log(obj[i]);
                var bool = confirm('Accept invite from '+obj[i]);
                console.log(bool);
                $.ajax({
                    url : 'ResponseRequest',
                    type : 'post',
                    data : {bool : bool.toString(), friend : obj[i], id : id},
                    success : alert('Success')
                })
            }
        }
    }
    $('#request').click(function(){
        $.ajax({
            url : 'CheckFriendRequest',
            type : 'post',
            dataType : 'JSON',
            data : {id : id},
            success: writeRequest
        })
    })
})