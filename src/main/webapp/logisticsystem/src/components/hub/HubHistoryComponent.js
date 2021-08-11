import React, { useEffect, useState } from 'react';
import axios from 'axios';
import HubMenu from '../hub/HubMenuComponent';
// import Calendar from 'react-calendar';
// import 'react-calendar/dist/Calendar.css';
import Cookies from 'universal-cookie';
axios.defaults.withCredentials = true
const HUB_REST_API_URL = 'http://localhost:8080/hub/history';


// const modDay = function(mod) {
//     let year = mod.getFullYear();
//     let month = (mod.getMonth()+1 < 10) ? "0"+(mod.getUTCMonth()+1) : mod.getMonth()+1;
//     let date = (mod.getDate() < 10) ? "0"+mod.getDate() : mod.getDate();

//     return year+"-"+month+"-"+date;
// }

// const getHistory =  function(fDay) {
//     let fullDate = modDay(fDay);
//     let data;
//     cookies.set("history", fullDate,{path:"/hub/history"});
//     axios.get(HUB_REST_API_URL).then(response => {
//         nData = response.data[0];
//     });
// }

const HubHistoryComponent = function () {

    // const [calendarValue, setCalendarValue] = useState(new Date());
    // const [allRoute, setAllRoute] = useState([]);
    // const [nDate, setnDate] = useState("");

    // useEffect(() => {

    //     setnDate(modDay(calendarValue));
    //     cookies.set("history", modDay(calendarValue),{path:"/hub/history"});
    //     axios.get(HUB_REST_API_URL).then(response => {
    //         setAllRoute(response.data);
    //     });
    // },[HUB_REST_API_URL]);

    return (
        <div className="container" style={{marginTop:"10vh"}}>
            <HubMenu />
            <div>
              HISTORY
            </div>
        </div>
    );
};

export default HubHistoryComponent