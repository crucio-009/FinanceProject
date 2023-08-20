function getLoanApplicationsByType(){
    
    const custid=localStorage.getItem('custid')
    const req = new XMLHttpRequest()

    const selectObject = document.getElementById('filterSelect')
    //'options' returns an array of all options present in the SELECT element
    const allOptions = selectObject.options
    //get the index of the selected option
    const index = selectObject.selectedIndex
    //now get the selected option object from the SELECT element
    const selectedOption = allOptions[index]
      //console.log(selectedOption.value);

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

                    // const tdcusId = document.createElement('td')
                    // tdcusId.innerText = data.customerId.toString()

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


                if(selectedOption.value=="PENDING"){
                    const tdDeleteElement = document.createElement("td");
                    // Create the "Delete" button
                    const deleteButton = document.createElement("button");
                    deleteButton.innerText = "Cancel";
                    deleteButton.classList.add('btn','btn-danger')

                    deleteButton.addEventListener("click", () => {
                        
                        const req=new XMLHttpRequest();

                        req.onreadystatechange = () =>{

                        if (req.status === 200 && req.readyState === 4) {
                            
                             window.alert("Loan Application Cancelled!");
                              location.reload();          

                            }

                        }
                        req.open('POST', `http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/delete/${tdId.innerText}`);
                        req.setRequestHeader("Content-Type","application/json")
                        req.send()
                    });

                    tdDeleteElement.appendChild(deleteButton);
                    const row = document.createElement('tr')
                    row.append(tdId,tdloanId,tdloanAmount,tdinterestRate,tdtenure,tdapplicationDate,tdapplicationStatus,tdremarks,tdImg,tdDeleteElement)
                    tableBody.append(row);
                    }
                    else{
                    const row = document.createElement('tr')
                    row.append(tdId,tdloanId,tdloanAmount,tdinterestRate,tdtenure,tdapplicationDate,tdapplicationStatus,tdremarks,tdImg)
                    tableBody.append(row);
                    }
                }
            }
        }

    req.open('GET', `http://localhost:8080/FinanceProjectAppDay2/rest/loan/applications/get/customer/${custid}?type=${selectedOption.value}`)
    const token=localStorage.getItem('token')
    req.setRequestHeader('Authorization', `Bearer ${token}`)
    req.send() 
}


window
    .addEventListener(
        'DOMContentLoaded',
        function () {
           //getCustomerDetails(),
            getLoanApplicationsByType(),
            this.document.getElementById('filterSelect').addEventListener('change',getLoanApplicationsByType);
        }
    )



// function getCustomerDetails(){
//     const userId=localStorage.getItem('userid');
//     //console.log(userId);
//     const req = new XMLHttpRequest();
//     req.onreadystatechange = function () {
//         if (req.status === 200 && req.readyState === 4) {
//             const resp2=JSON.parse(req.responseText);
//             getLoanApplicationsByType(resp2.responseData)

//         }
//     }
//     req.open('GET',`http://localhost:8080/FinanceProjectAppDay2/rest/customers/get?email=${userId}`,true)
//     req.setRequestHeader('Content-Type', 'application/json')
//     req.send()
// }

