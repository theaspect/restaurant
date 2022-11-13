import * as React from "react"
import {useCallback, useEffect, useState} from "react";

function Echo1() {
    const [echo, setEcho] = useState("Pending echo");
    const [date, setDate] = useState("Pending Date");

    function refresh() {
        fetch(ECHO2_URL + '/echo?value=Response+from+server')
            .then((response) => response.text())
            .then((echo) => setEcho(echo))
            .catch((error) => setEcho("An error occurred!"));

        fetch(ECHO2_URL + '/dbTime')
            .then((response) => response.text())
            .then((date) => setDate(date))
            .catch((error) => setDate("An error occurred!"));
    }

    useEffect(() => {
        refresh()
    }, []);

    const onClick = useCallback(() => refresh(), [])

    return (
        <>
            <h1>Echo2</h1>
            <div><strong>Echo</strong>: {echo}</div>
            <div><strong>Server date</strong>: {date}</div>
            <button onClick={onClick}>Refresh</button>
        </>
    )
}

export default Echo1