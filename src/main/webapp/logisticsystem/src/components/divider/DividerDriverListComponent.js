import React, { useEffect, useState } from "react";
import axios from "axios";
import DividerMenu from "../divider/DividerMenuComponent";
import Cookies from'universal-cookie'
const cookie = new Cookies()

/*
    @author The Phap 
 */

axios.defaults.withCredentials = true;
const DIVIDER_REST_API_URL = "http://localhost:8080/divider/driver_list";

const DriverDetail = function (props) {
  return (
    <div className="card shadow-lg  bg-gray rounded ">
      <div className="card-header  "  style={{paddingTop:'20px',backgroundColor:"#6c7ae0"}}>
        <h5 className="text-white">
          <strong>{props.driver.name + " (" + props.driver.staff_id + ")"}</strong>
        </h5>
      </div>
      <div className="card-body">
        <p >
          <p>
            <b>Phone Number: </b> {props.driver.phone_num}
          </p>
          <hr/>
          
          <p>
            <b>Current Hub: </b> {props.driver.current_hub}
          </p>
        </p>
      </div>
    </div>
  );
};
const DividerDriverListComponent = function (props) {
  const [driverList, setDriverList] = useState([]);
  const [driver, setDriver] = useState({});
  const [checked, setChecked] = useState(true);
  useEffect(() => {
    if (typeof cookie.get('divider_id') == 'undefined')  window.location.assign('http://localhost:3000/exception/403');
    axios.get(DIVIDER_REST_API_URL).then((response) => {
      setDriverList(response.data);
      setDriver(response.data[0]);
    });
  }, []);

  return (
    <div className="container">
      <DividerMenu />
      <h1 className="mx-auto text-center" style={{marginTop:"70px",padding:"30px",color:"#808080"}}><b>DRIVER LIST</b></h1>
      <div
        className="driver-list-show row justify-content-around"
        style={{ height: "60vh" }}
      >
        <div className="driver-list col-md-7 mh-100 p-0 overflow-auto shadow-lg  bg-gray rounded">
          <table className="table table-striped " style={{lineHeight:"4vh"}}>
            <thead style={{backgroundColor:"#6c7ae0",position: "sticky", top: "0", zIndex: "1"}}>
              <tr  className="p-3 mb-5 rounded text-white "  >
                <th scope="col" >DRIVER ID</th>
                <th scope="col">DRIVER NAME</th>
                <th scope="col">DETAIL</th>
              </tr>
              {/* style={{position: "sticky", top: "0", zIndex: "1"}} */}
            </thead>
            <tbody>
              {driverList.map((driverDetail) => (
                <tr key={"driver" + driverDetail.staff_id}>
                  <th scope="row">{driverDetail.staff_id}</th>
                  <td>{driverDetail.name}</td>
                  <td>
                    <div className="form-check">
                      {driverDetail.staff_id === driver.staff_id ? (
                        <input
                          className="form-check-input"
                          type="radio"
                          name="flexRadioDefault"
                          id={"flexRadioDefault" + driverDetail.staff_id}
                          onClick={() => {
                            setDriver(driverDetail);
                          }}
                          defaultChecked={checked}
                          onChange={() => setChecked(!checked)}
                        />
                      ) : (
                        <input
                          className="form-check-input"
                          type="radio"
                          name="flexRadioDefault"
                          id={"flexRadioDefault" + driverDetail.staff_id}
                          onClick={() => {
                            setDriver(driverDetail);
                          }}
                        />
                      )}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <div className="shipper-detail col-md-4">
          <DriverDetail driver={driver} />
        </div>
      </div>
    </div>
  );
};
 
export default DividerDriverListComponent;
