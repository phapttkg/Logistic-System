import React, { useEffect, useState } from "react";
import axios from "axios";
import DriverMenu from "./DriverMenuComponent";
import Cookies from "universal-cookie";
const cookie = new Cookies();

axios.defaults.withCredentials = true;


/*
    @author LOng deep try
 */

const DRIVER_REST_API_URL = "http://localhost:8080/driver/container";
const DRIVER_ACTION_URL = "http://localhost:8080/driver/container/sendtracking.do"

const sendTracking = function(props) {
  const cookies = new Cookies();
  cookies.set('routeID', props,{path:'/driver/container'});
  axios.put(DRIVER_ACTION_URL);
  window.location.reload();
}

const DriverPackageComponent = function (props) {

  const [route, setRoute] = useState({});
  const[pkg, setPkg]=useState([]);

  useEffect(() => {
    if (typeof cookie.get('driver_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    axios.get(DRIVER_REST_API_URL).then((response) => {
      setRoute(response.data);
      setPkg(response.data.packagelist);
    });
      
  },[]);

  return (
    <div className="container">
      <DriverMenu/>
      <h1 className="text-center" style={{ padding: "30px", marginTop:"70px" }}>
        CONTAINER
      </h1>

        <div className="row shadow-lg p-3 mb-5 bg-gray rounded" style={{ border: "1px black solid", marginBottom:"5px", padding:"10px" }}>
          <h4 className="text-center mb-3">Route {route.start_pos} - {route.end_pos}</h4>
          <div className=" col-6 ">
            <div className="card bg-light mb-3">
              <div className="card-header"><b>Route ID: {route.route_id}</b></div>
              <div className="card-body">
                <p className="card-text">
                <span><b>Driver ID: </b> {route.driver_id}</span> <br></br>
                  <span><b>Star hub: </b> {route.start_pos}</span> <br></br>
                  <span><b>End hub: </b>{route.end_pos} </span> <br></br>
                  <span><b>Star Date: </b>{route.start_datetime} </span>                  
                </p>
                <p>
                  {
                  (typeof pkg[0] !== 'undefined' && (splitLastStatus(pkg[0].tracking_status).includes('sending') || splitLastStatus(pkg[0].tracking_status).includes('arriving')))
                  ? 
                  (typeof pkg[0] !== 'undefined' && splitLastStatus(pkg[0].tracking_status).includes('sending') && !splitLastStatus(pkg[0].tracking_status).includes('arriving'))
                  ?
                  <button className="btn btn-info" style={{width:"100%"}} onClick={() => sendTracking(route.route_id)}><b>ARRIVING</b></button>
                  :
                  <button className="btn btn-info" style={{width:"100%"}} onClick={() => sendTracking(route.route_id)}><b>ARRIVED</b></button>
                  :
                  (typeof pkg[0] !== 'undefined' && !splitLastStatus(pkg[0].tracking_status).includes('arrived'))
                  ?
                  <b>HUB HASN'T EXPORTED YET</b>
                  :
                  <b>DONE</b>
                  }
                </p>
              </div>
            </div>
          </div>
          <div className="col-6" id="accordion">
            <span className="card card-header bg-light"><b>Package List</b></span>
            {pkg.map((pkg)=>(
            <div className="card" key={pkg.pkg_id}>
              <div className="card-header">
                  <button
                    className="btn btn-info"
                    data-toggle="collapse"
                    data-target={"#collapse"+pkg.pkg_id}
                    aria-controls={"collapse"+pkg.pkg_id}
                  >
                   <b>Package ID: {pkg.pkg_id}</b> 
                  </button>
              </div>
              <div
                id={"collapse"+pkg.pkg_id}
                className="collapse"
                style={{width:"100%"}}>
                <div className="card-body">
                  <span><b>Created date: </b> {pkg.created_datetime}</span> <br></br>
                  <span><b>Current hub: </b>{pkg.current_hub} </span> <br></br>
                  <span><b>Next hub: </b>{pkg.next_hub} </span> <br></br>
                  <span><b>From </b>{pkg.sender_address} </span> <br></br>
                  <span><b>To </b>{pkg.receiver_address} </span> <br></br>
                </div>
              </div>
            </div>
            ))}
          </div>
        </div>
    </div>
  );
};

const splitLastStatus = function(status) {
  const splitStatus = status.split('-');
  const index = splitStatus.length - 1;
  return splitStatus[index];
};


export default DriverPackageComponent;
