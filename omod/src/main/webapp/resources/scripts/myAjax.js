/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getCohorts(type, cohortAjaxUrl, callback)
{
    jq = jQuery;
   /* jq.ajax({
        url:cohortAjaxUrl,
        type:"GET",
        data:{state:"start"},
        success:function(response){
            console.log(response);
        },
        error:function(xhr, status, error){
             console.log(error);
        }
    })*/
    jq.getJSON(cohortAjaxUrl,
            {
              type: type,
             
            }, function(data){
                callback(data);
            }, function(xhr, status, error){
                console.log(error);
            })
        
}


