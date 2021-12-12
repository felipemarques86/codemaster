import {Component, OnInit} from '@angular/core';
import {AnalyticsReportService} from "../../services/analytics-report.service";

@Component({
  selector: 'app-analytics-activity',
  templateUrl: './analytics-activity.component.html',
  styleUrls: ['./analytics-activity.component.css']
})
export class AnalyticsActivityComponent implements OnInit {

  constructor(private activityReportService: AnalyticsReportService) { }

  ngOnInit(): void {
  }

}
