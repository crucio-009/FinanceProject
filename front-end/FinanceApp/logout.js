document.getElementById('btnlogout').addEventListener('click',function(){
    console.log("YESS")
    localStorage.clear();
    // window.open("/index.html")
})

function displayEmail(){
    const email=localStorage.getItem('userid');
    //console.log(email)
    const e=document.getElementById('emailElement');
    e.innerText=email;

    //console.log(e.value)
}

window.addEventListener('DOMContentLoaded',
    function(){
        displayEmail()
        
    }
)