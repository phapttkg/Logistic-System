import React, { useEffect, useState } from "react";
import axios from "axios";
import DriverMenu from "./DriverMenuComponent";
import Cookies from "universal-cookie";
const cookie = new Cookies();


/*
    @author Long
 */
axios.defaults.withCredentials = true;
const DRIVER_REST_API_URL = "http://localhost:8080/driver/history";
const DriverHistoryComponent = function (props) {
  const [routeList, setRouteList] = useState([]);

   useEffect(() => {
    if (typeof cookie.get('driver_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    getRoute();
  }, []);

  let idValue = null;
  // let defaultId = null;
  // const HandleEdit =function(props){
  //  idValue = props.target.value;
  //  console.log(idValue);
  //  console.log(props.target.value);
  // }


  // const HandleUpdate = function(props){
 
  //   const url = 'http://localhost:8080/divider/route/modify.do';
  //         const data = 
  //               {
  //                   route_id : props.route_id,
  //                   driver_id: idValue,
                         
  //               } ;    
  //         axios.post(url,data).then(function (response) {
  //             window.location.reload();
  //         })
  //         console.log(data)     
  // }

  const getRoute=() =>{
    axios.get(DRIVER_REST_API_URL)
    .then((response) => {
      setRouteList(response.data);  
    });
  }
  return (
    <div class="container">
      <DriverMenu />
      <h1 class="text-center" style={{ padding: "30px", marginTop:"70px" }}>
        ROUTE LIST HISTORY
      </h1>
      
      {routeList.map((routeDetail) => (

        <div class="row shadow-lg p-3 mb-5 bg-gray rounded" style={{ border: "1px black solid", marginBottom:"5px", padding:"10px" }}>
          <h4 class="text-center mb-3">Route {routeDetail.start_pos} - {routeDetail.end_pos}</h4>
          <div class=" col-6 ">
            <div className="card bg-light mb-3">
              <div className="card-header"><b>Route ID: {routeDetail.route_id}</b></div>
              <div className="card-body">
                <p className="card-text">
                <span><b>Driver ID: </b> {routeDetail.driver_id}</span> <br></br>
                  <span><b>Star hub: </b> {routeDetail.start_pos}</span> <br></br>
                  <span><b>End hub: </b>{routeDetail.end_pos} </span> <br></br>
                  <span><b>Star Date: </b>{routeDetail.start_datetime} </span>
                </p>
              </div>
            </div>
          </div>
          <div class="col-6" id="accordion">
            <span class="card card-header bg-light"><b>Package List</b></span>
            {routeDetail.packagelist.map((pkg)=>(
            <div class="card">
              <div class="card-header">
                  <button
                    class="btn btn-info"
                    data-toggle="collapse"
                    data-target={"#collapse"+pkg.pkg_id}
                    aria-controls={"collapse"+pkg.pkg_id}
                  >
                   <b>Package ID: {pkg.pkg_id}</b> 
                  </button>
              </div>
              <div
                id={"collapse"+pkg.pkg_id}
                class="collapse"
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
          <div class="col-4">
            <div className="card bg-light mb-3">
              {/* <div className="card-header">
                  <span><b>Enter DriverID to Change</b></span>   
              </div>
              {/* <div className="card-body d-flex">
                    <input style={{marginRight:"10px"}}  className="form-control"  placeholder="Driver ID" defaultValue={routeDetail.driver_id} onChange={HandleEdit.bind(this)} ></input>
              <button type="button" class="btn btn-info" onClick={() => HandleUpdate(routeDetail)} >Submit</button>              
              </div> */} 
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};


export default DriverHistoryComponent;
