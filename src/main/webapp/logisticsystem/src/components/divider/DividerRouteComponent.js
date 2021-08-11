import React, { useEffect, useState } from "react";
import axios from "axios";
import DividerMenu from "./DividerMenuComponent";
import Cookies from'universal-cookie'
const cookie = new Cookies()

/*
    @author The Phap 
 */
axios.defaults.withCredentials = true;
const DIVIDER_REST_API_URL = "http://localhost:8080/divider/route";
const DRIVER_REST_API_URL = "http://localhost:8080/divider/driver_list";


const DividerRouteComponent = function (props) {
  const [routeList, setRouteList] = useState([]);
  const [driverID, setDriverID] = useState("");
  const [driver, setDriverList] = useState([]);

  useEffect(() => {
    if (typeof cookie.get('divider_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    axios.get(DIVIDER_REST_API_URL).then((response) => {
      setRouteList(response.data);
    });
     axios.get(DRIVER_REST_API_URL).then((response)=>{
       setDriverList(response.data);
     })
   
  }, []);

  
  const getDriverByHub= function(props){
    const listDriver=[];
    driver.forEach((item)=>{
      if(item.current_hub===props){
        listDriver.push(item);
      }
    })
    return listDriver;
  }

  const HandleUpdate = function (props) {
    const url = "http://localhost:8080/divider/route/modify.do";
    const data = {
      route_id: props.route_id,
      driver_id: driverID,
    };
    axios.post(url, data).then(function (response) {
      window.location.reload();
    });
    console.log(data);
  };
  

  return (
    <div class="container">
      <DividerMenu />

      <h1
        className="mx-auto text-center"
        style={{ marginTop: "100px", color: "#808080" }}
      >
        <b>ROUTE LIST</b>
      </h1>

      {routeList.map((routeDetail) => (
        <div
          class="row shadow-lg p-3 mb-5 bg-gray rounded"
          style={{ marginBottom: "5px", padding: "10px" }}
        >
          <h4
            class="text-center text-white mb-3 rounded"
            style={{ backgroundColor: "#6c7ae0", padding: "10px" }}
          >
            <b>
              Route {routeDetail.start_pos} - {routeDetail.end_pos}
            </b>
          </h4>
          <div class=" col-4 ">
            <div className="card bg-light mb-3">
              <div className="card-header bg-secondary text-white">
                <b>Route ID: {routeDetail.route_id}</b>
              </div>
              <div className="card-body">
                <p className="card-text">
                  <p>
                    <b>Driver ID: </b> {routeDetail.driver_id}
                  </p>{" "}
                  <hr />
                  <p>
                    <b>Star hub: </b> {routeDetail.start_pos}
                  </p>{" "}
                  <hr />
                  <p>
                    <b>End hub: </b>
                    {routeDetail.end_pos}{" "}
                  </p>{" "}
                  <hr />
                  <p>
                    <b>Star Date: </b>
                    {routeDetail.start_datetime}{" "}
                  </p>
                </p>
              </div>
            </div>
          </div>
          <div class="col-4 " id="accordion">
            <p class="card card-header bg-secondary text-white">
              <b>Package List</b>
            </p>
            {routeDetail.packagelist.map((pkg) => (
              <div class="card ">
                <div class="card-header">
                  <button
                    class="btn btn-primary"
                    data-toggle="collapse"
                    data-target={"#collapse" + pkg.pkg_id}
                    aria-controls={"collapse" + pkg.pkg_id}
                  >
                    <b>Package ID: {pkg.pkg_id}</b>
                  </button>
                </div>
                <div
                  id={"collapse" + pkg.pkg_id}
                  class="collapse"
                  style={{ width: "100%" }}
                >
                  <div className="card-body">
                    <p>
                      <b>Created date: </b> {pkg.created_datetime}
                    </p>{" "}
                    <hr />
                    <p>
                      <b>Current hub: </b>
                      {pkg.current_hub}{" "}
                    </p>{" "}
                    <hr />
                    <p>
                      <b>Next hub: </b>
                      {pkg.next_hub}{" "}
                    </p>{" "}
                    <hr />
                    <p>
                      <b>From </b>
                      {pkg.sender_address}{" "}
                    </p>{" "}
                    <hr />
                    <p>
                      <b>To </b>
                      {pkg.receiver_address}{" "}
                    </p>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div class="col-4">
            <div className="card bg-light">
              <div className="card-header bg-secondary text-white">
                <p>
                  <b>Enter DriverID to Change</b>
                </p>
              </div>
              <div className="card-body d-flex">
                <form>
                <select
                  class="form-select"
                  onChange={(e) => {
                    const valueID = e.target.value;
                    setDriverID(valueID);
                  }}
                >
                  <option selected>Driver ID</option>
                  {getDriverByHub(routeDetail.start_pos).map((driver) => (
                    <option value={driver.staff_id}>{driver.staff_id}</option>
                  ))}
                </select>
                </form>
                <button
                  type="button"
                  class="btn btn-primary"
                  onClick={() => HandleUpdate(routeDetail)}
                >
                  Submit
                </button>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default DividerRouteComponent;
