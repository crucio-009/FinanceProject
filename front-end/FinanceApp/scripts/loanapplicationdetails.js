function getLoanApplications(){
    
    const req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            //console.log(req.responseText)
            const jsData = JSON.parse(req.responseText)
            const tableBody = document.getElementById('tblBody')
            tableBody.innerHTML = ""
            //console.log(jsData)

            for (const data of jsData.responseData) {

                const tdId = document.createElement('td')
                tdId.innerText = data.applicationId.toString()

                const tdcusId = document.createElement('td')
                tdcusId.innerText = data.customerId.toString()

                const tdloanId = document.createElement('td')
                tdloanId.innerText = data.loanId.toString()
                
                const tdloanAmount = document.createElement('td')
                tdloanAmount.innerText = data.loanAmount.toString()

                const tdinterestRate = document.createElement('td')
                tdinterestRate.innerText = data.interestRate.toString()

                const tdtenure = document.createElement('td')
                tdtenure.innerText = data.tenure.toString()

                const tdapplicationDate = document.createElement('td')
                tdapplicationDate.innerText = data.applicationDate.toString().slice(0,-1)
                //tdapplicationDate.innerText = data.applicationDate.toString()

                const tdapplicationStatus = document.createElement('td')
                tdapplicationStatus.innerText = data.applicationStatus.toString()

                const tdremarks= document.createElement('td')
                tdremarks.innerText = (data.remarks==undefined)?"-":data.remarks.toString()

                // const tdcusId = document.createElement('td')
                // tdcusId.innerText = data.applicationDate.toString()

                const imgEle = document.createElement('img')
                imgEle.src = data.documentOne
                imgEle.alt = "NA"
                imgEle.style.width = "200px";
                imgEle.style.margin = "2px";

                const tdImg = document.createElement('td')
                tdImg.appendChild(imgEle)

                const row = document.createElement('tr')
                row.append(tdId,tdcusId,tdloanId,tdloanAmount,tdinterestRate,tdtenure,tdapplicationDate,tdapplicationStatus,tdremarks,tdImg)

                tableBody.append(row)
            }
        }
    }
    req.open('GET', 'http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/all')
    const token=localStorage.getItem('token')
    req.setRequestHeader('Authorization', `Bearer ${token}`)
    req.send() 
}

function displayinfo(alldata){
    const t = document.getElementById("tblBody")
    while(t.firstChild){
        t.removeChild(t.firstChild)
    }
    for (const data of alldata) {

        const tdId = document.createElement('td')
        tdId.innerText = data.applicationId.toString()

        const tdcusId = document.createElement('td')
        tdcusId.innerText = data.customerId.toString()

        const tdloanId = document.createElement('td')
        tdloanId.innerText = data.loanId.toString()
        
        const tdloanAmount = document.createElement('td')
        tdloanAmount.innerText = data.loanAmount.toString()

        const tdinterestRate = document.createElement('td')
        tdinterestRate.innerText = data.interestRate.toString()

        const tdtenure = document.createElement('td')
        tdtenure.innerText = data.tenure.toString()

        const tdapplicationDate = document.createElement('td')
        tdapplicationDate.innerText = data.applicationDate.toString().slice(0,-1)
        //tdapplicationDate.innerText = data.applicationDate.toString()

        const tdapplicationStatus = document.createElement('td')
        tdapplicationStatus.innerText = data.applicationStatus.toString()

        const tdremarks= document.createElement('td')
        tdremarks.innerText = (data.remarks==undefined)?"-":data.remarks.toString()

        // const tdcusId = document.createElement('td')
        // tdcusId.innerText = data.applicationDate.toString()

        const imgEle = document.createElement('img')
        imgEle.src = data.documentOne
        imgEle.alt = "NA"
        imgEle.style.width = "200px";
        imgEle.style.margin = "2px";

        const tdImg = document.createElement('td')
        tdImg.appendChild(imgEle)

        const row = document.createElement('tr')
        row.append(tdId,tdcusId,tdloanId,tdloanAmount,tdinterestRate,tdtenure,tdapplicationDate,tdapplicationStatus,tdremarks,tdImg)

        t.append(row)
    }
}

document.getElementById("fetch").addEventListener('click',()=>{
    const selectObject = document.getElementById('searchDropdownBox')
    //'options' returns an array of all options present in the SELECT element
    const allOptions = selectObject.options
    //get the index of the selected option
    const index = selectObject.selectedIndex
    //now get the selected option object from the SELECT element
    const selectedOption = allOptions[index]

    // console.log(selectedOption)

    const req = new XMLHttpRequest()

    req.onreadystatechange = ()=>{
        if(req.status === 200 && req.readyState === 4){
            const obj = JSON.parse(req.responseText)
            displayinfo(obj.responseData)
        }
    }

    if(selectedOption.value === "I"){
        const inputele = document.getElementById("idval").value
        console.log(inputele)
        
    }
    else if(selectedOption.value === "T"){
        const inputele = document.getElementById("typeval").value
        console.log(inputele)
    }
    else if(selectedOption.value === "D"){
        const inputele = document.getElementById("dateval").value
        console.log(inputele)
        req.open('GET',`http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/get/date?date=${inputele}`)
        req.send()
    }
})

function loadrequired(){
    const selectObject = document.getElementById('searchDropdownBox')
    //'options' returns an array of all options present in the SELECT element
    const allOptions = selectObject.options
    //get the index of the selected option
    const index = selectObject.selectedIndex
    //now get the selected option object from the SELECT element
    const selectedOption = allOptions[index]

    const divele = document.getElementById("display")
    while(divele.firstChild){
        divele.removeChild(divele.firstChild)
    }
    if(selectedOption.value === "I"){
        const inputele = document.createElement("input")
        inputele.type = "number"
        inputele.id = "idval"
        divele.appendChild(inputele)
    }
    else if(selectedOption.value === "T"){
        const inputele = document.createElement("input")
        inputele.type = "text"
        inputele.id = "typeval"
        divele.appendChild(inputele)
    }
    else if(selectedOption.value === "D"){
        const inputele = document.createElement("input")
        inputele.type = "date"
        inputele.id = "dateval"
        divele.appendChild(inputele)
    }
    
}

window
    .addEventListener(
        'DOMContentLoaded',
        function () {
             getLoanApplications()
             loadrequired()
             this.document.getElementById("searchDropdownBox").addEventListener('change',loadrequired)
        }
    )
