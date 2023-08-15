document.getElementById("role").addEventListener('change', function(){
    localStorage.setItem('role_id', this.value);
    sessionStorage.setItem('role_id', this.value);
    document.querySelector("body > div.container-fluid.d-flex.align-items-center.py-4.hero > main > div > span > a").href=""
});


document.getElementsByClassName('signup-link')[0].addEventListener('click', function(){
    if(localStorage.getItem('role_id') == 1){
        this.href="usersignup.html";
    }
    else if(localStorage.getItem('role_id') == 2){
        this.href="managersignup.html";
    }
    else if(localStorage.getItem('role_id') == 3){
        this.href="clerksignup.html";
    }
    else{
        this.href="login.html"
    }
});