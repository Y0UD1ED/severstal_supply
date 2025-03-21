import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import Report from './Report';
import {format} from "date-fns"

const URL="http://localhost:8080"

function App() {
  

  const [providers,setProviders]=useState([])
  const [provider,setProvider]=useState(providers[0])
  const [products,setProducts]=useState()

  const [appleOne,setAppleOne]=useState({
    "id":0,
    "price":0,
    "weight":0})
  const [appleTwo,setAppleTwo]=useState({
    "id":0,
    "price":0,
    "weight":0})

  const [pearOne,setPearOne]=useState({
    "id":0,
    "price":0,
    "weight":0})

  const [pearTwo,setPearTwo]=useState({
    "id":0,
    "price":0,
    "weight":0})

  const [dateSupply,setDateSupply]=useState(new Date())

  const [report,setReport]=useState([])
  const [loading,setLoading]=useState(true)
  const [startDate,setStartDate]=useState(new Date())
  const [endDate,setEndDate]=useState(new Date())



useEffect(() =>{
  const fetchProv = async () => {
    try {
      const  prov= 
        await fetch(`${URL}/providers`)
      if (!prov.ok) {
        throw new Error('Network response was not ok');
      }
      const provRes = await prov.json();
      setProviders(provRes)
      setProvider(provRes[0].id)
    } catch (error) {
      console.log(error)
    }
  } 
  
    const fetchProd = async () => {
      try {
        const  prod= 
          await fetch(`${URL}/products`)
        if (!prod.ok) {
          throw new Error('Network response was not ok');
        }
        const prodRes = await prod.json();
        setProducts(prodRes)
        setAppleOne(prev=>({...prev,["id"]:prodRes["Яблоко"][0].id}))
        setAppleTwo(prev=>({...prev,["id"]:prodRes["Яблоко"][1].id}))
        setPearOne(prev=>({...prev,["id"]:prodRes["Груша"][0].id}))
        setPearTwo(prev=>({...prev,["id"]:prodRes["Груша"][1].id}))
      } catch (error) {
        console.log(error)
      } 
  };
  
  const getData=async()=>{
    try{

      await fetchProv()
      await fetchProd()
    }catch(e){
      console.log(e)
    }finally{
      setLoading(false)
    }
  }
getData()},[]);

  const acceptSupply=async()=>{
    try{
    const acceptedProduct=[appleOne,appleTwo,pearOne,pearTwo]
    const jsBody={providerId:provider,products:acceptedProduct,supplyDate:format(dateSupply,"dd-MM-yyyy")}
    const options = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(jsBody)
    };
    const res=await fetch(`${URL}/supplies`,options);
    if (res.ok) {
      window.location.reload()
    }
  }catch(e){
    console.log(e)
  }
}

const getReport=async()=>{
  try{
    const date1=format(startDate,"dd-MM-yyyy")
    const date2=format(endDate,"dd-MM-yyyy")
    const res=await fetch(`${URL}/supplies/report?startDate=${date1}&endDate=${date2}`)
    if (!res.ok) {
      throw new Error('Network response was not ok');
    }
    const repRes = await res.json();
    setReport(repRes)
  }catch(e){
    console.log(e)
  }
}

  if(loading){
    return(<div></div>)
  }

  return (
    <div className="App">
      <div className='supply'>
        <label>Поставщик:
          <select value={provider} onChange={(e)=>setProvider(e.target.value)}>
              {providers.map(option => (
              <option key={option.id} value={option.id}>
                  {option.name}
              </option>
              ))}
          </select>
        </label>

        <label>Яблокo 1 вид:
          <select value={appleOne.id} onChange={(e)=>setAppleOne(prev=>({...prev,["id"]:e.target.value}))}>
              {products["Яблоко"].map(option => (
              <option key={option.id} value={option.id}>
                  {option.name}
              </option>
              ))}
          </select>
          <label>Вес: 
            <input 
              type='number'
              placeholder='Вес'
              value={appleOne.weight}
              onChange={(e)=>setAppleOne(prev=>({...prev,["weight"]:e.target.value}))}/>
          </label>
          <label>Цена: 
            <input 
              type='number'
              placeholder='Цена'
              value={appleOne.price}
              onChange={(e)=>setAppleOne(prev=>({...prev,["price"]:e.target.value}))}/>
          </label>
        </label>

        <label>Яблокo 2 вид:
          <select value={appleTwo.id} onChange={(e)=>setAppleTwo(prev=>({...prev,["id"]:e.target.value}))}>
              {products["Яблоко"].map(option => (
              <option key={option.id} value={option.id}>
                  {option.name}
              </option>
              ))}
          </select>
          <label>Вес: 
            <input 
              type='number'
              placeholder='Вес'
              value={appleTwo.weight}
              onChange={(e)=>setAppleTwo(prev=>({...prev,["weight"]:e.target.value}))}/>
          </label>
          <label>Цена:
            <input 
              type='number'
              placeholder='Цена'
              value={appleTwo.price}
              onChange={(e)=>setAppleTwo(prev=>({...prev,["price"]:e.target.value}))}/>
          </label>
        </label>

        <label>Груша 1 вид:
          <select value={pearOne.id} onChange={(e)=>setPearOne(prev=>({...prev,["id"]:e.target.value}))}>
              {products["Груша"].map(option => (
              <option key={option.id} value={option.id}>
                  {option.name}
              </option>
              ))}
          </select>
          <label>Вес:
            <input 
              type='number'
              placeholder='Вес'
              value={pearOne.weight}
              onChange={(e)=>setPearOne(prev=>({...prev,["weight"]:e.target.value}))}/>
          </label>
          <label>Цена:
            <input 
              type='number'
              placeholder='Цена'
              value={pearOne.price}
              onChange={(e)=>setPearOne(prev=>({...prev,["price"]:e.target.value}))}/>
            </label>
        </label>

        <label>Груша 2 вид:
          <select value={pearTwo.id} onChange={(e)=>setPearTwo(prev=>({...prev,["id"]:e.target.value}))}>
              {products["Груша"].map(option => (
              <option key={option.id} value={option.id}>
                  {option.name}
              </option>
              ))}
          </select>
          <label>Вес:
            <input 
              type='number'
              placeholder='Вес'
              value={pearTwo.weight}
              onChange={(e)=>setPearTwo(prev=>({...prev,["weight"]:e.target.value}))}/>
          </label>
          <label>Цена:
            <input 
              type='number'
              placeholder='Цена'
              value={pearTwo.price}
              onChange={(e)=>setPearTwo(prev=>({...prev,["price"]:e.target.value}))}/>
          </label>
        </label>
        <div className='save'>
        <label> Дата поставки</label>
        <input 
          type='date'
          value={dateSupply}
          onChange={(e)=>setDateSupply(e.target.value)} 
        />
        <br></br>
        <button onClick={()=>acceptSupply()}>Принять поставку</button>
        </div>
      </div>
      <div className='report'>
        <p>Выберете период</p>
        <label>Начало</label>
        <input 
          type='date'
          value={startDate}
          onChange={(e)=>setStartDate(e.target.value)}/>
        <br></br>
        <label>Окончание</label>
        <input 
          type='date'
          value={endDate}
          onChange={(e)=>setEndDate(e.target.value)}/>
        <br></br>
        <button onClick={()=>getReport()}>Показать отчет</button>
        <div>
        {report.map(r=>
          <div className='provider_wrapper'>
            <Report provider={r}/>
          </div>
        )}
        </div>
      </div>
    </div>
  );
}

export default App;
