function sendAsyncRequest(httpMethodName, url, callbackFn, contentType = "application/json", reqBodyData = undefined) {
    var req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if (req.status === 200 && req.readyState === 4) {
            callbackFn(req.responseText)
        }
    }
    req.open(httpMethodName, url, true)
    //req.setRequestHeader('Content-Type', 'application/json')
    req.setRequestHeader('Content-Type', contentType)

    if (localStorage.getItem('token')) {
        req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem('token')}`)
    }

    if (reqBodyData)
        req.send(JSON.stringify(reqBodyData))
    else
        req.send()
}